package com.example.lab1.ui.signalstrengthlist

import android.content.Context
import android.content.Intent
import androidx.fragment.app.viewModels
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.InvalidationTracker

import com.example.lab1.R
import com.example.lab1.databinding.FragmentNotificationsBinding
import com.example.lab1.databinding.FragmentSignalStrengthListBinding
import com.example.lab1.ui.notifications.NotificationsViewModel
import com.example.lab1.data.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class Signal_strength_list : Fragment() {

    private var _binding: FragmentSignalStrengthListBinding? = null
    private lateinit var signalStrengthListViewModel: SignalStrengthListViewModel
    private lateinit var sensorDataAdapter: SensorDataAdapter
    private lateinit var recyclerView: RecyclerView

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.fragment_signal_strength_list, container, false)
        _binding = FragmentSignalStrengthListBinding.inflate(inflater, container, false)


        recyclerView = root.findViewById(R.id.signal_strength_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)

        signalStrengthListViewModel = SignalStrengthListViewModel(requireActivity().application)
        signalStrengthListViewModel.userList.observe(viewLifecycleOwner,
            Observer { users ->
                sensorDataAdapter.setUsers(users)
            })

        sensorDataAdapter = SensorDataAdapter(emptyList()) {mac ->
            val bundle = Bundle().apply {
                putString("mac",mac)
            }
            findNavController().navigate(R.id.action_list_to_edit,bundle)
        }
        recyclerView.adapter=sensorDataAdapter

        // floating action button navigation logic
        val fab: FloatingActionButton = root.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            //Log.d("NotificationsFragment", "Button clicked!")

            val newUser = User(0, "MAC", "-1","Sensorius")
            signalStrengthListViewModel.saveUser(newUser)
            signalStrengthListViewModel.saveUser(newUser)
            signalStrengthListViewModel.saveUser(newUser)
            signalStrengthListViewModel.update()

            signalStrengthListViewModel.userSaved.observe(viewLifecycleOwner) {  isSaved ->
                if (isSaved) {

                    lifecycleScope.launch {
                        val selectedMac = signalStrengthListViewModel.getSelected()  // Call the suspend function
                        val bundle = Bundle().apply {
                            putString("mac", selectedMac)
                        }
                        findNavController().navigate(R.id.action_list_to_edit, bundle)
                    }
                }

            }

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}