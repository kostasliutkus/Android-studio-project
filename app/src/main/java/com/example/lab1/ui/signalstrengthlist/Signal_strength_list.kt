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
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.InvalidationTracker

import com.example.lab1.R
import com.example.lab1.databinding.FragmentNotificationsBinding
import com.example.lab1.databinding.FragmentSignalStrengthListBinding
import com.example.lab1.ui.notifications.NotificationsViewModel
import com.example.lab1.data.ReadStiprumai
import com.example.lab1.data.ReadUsers
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class Signal_strength_list : Fragment() {

    private var _binding: FragmentSignalStrengthListBinding? = null
    private lateinit var signalStrengthListViewModel: SignalStrengthListViewModel
    private lateinit var sensorDataAdapter: SensorDataAdapter
    private lateinit var recyclerView: RecyclerView
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val signalStrengthListViewModel =
//            ViewModelProvider(this).get(SignalStrengthListViewModel::class.java)
        var userEditModel = SignalStrengthListViewModel(requireActivity().application);
        val root = inflater.inflate(R.layout.fragment_signal_strength_list, container, false)
        _binding = FragmentSignalStrengthListBinding.inflate(inflater, container, false)


//        val textView: TextView = binding.textSignalstrengthlist
//        signalStrengthListViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        // Call ReadStiprumai with the context and handle potential exceptions
//        val usersList = try {
//            ReadUsers(requireContext())
//        } catch (e: Exception) {
//            e.printStackTrace()
//            emptyList() // Fallback to empty list in case of error
//        }

//        var recyclerView = binding.signalStrengthListRecyclerView
//
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        sensorDataAdapter = SensorDataAdapter(usersList)
//        recyclerView.adapter=sensorDataAdapter


        recyclerView = root.findViewById(R.id.signal_strength_list_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        sensorDataAdapter = SensorDataAdapter(emptyList())
        recyclerView.adapter = sensorDataAdapter

        //val root: View = binding.root
        signalStrengthListViewModel = SignalStrengthListViewModel(requireActivity().application)
        signalStrengthListViewModel.userList.observe(viewLifecycleOwner,
            Observer { users ->
                sensorDataAdapter.setUsers(users)
            })
        //signalStrengthListViewModel.clearUsers()
//        signalStrengthListViewModel.loadUsersFromJson()
//        signalStrengthListViewModel.userList.observe(viewLifecycleOwner) { userList ->
//            Log.d(
//                "CBB",
//                "USER liST ${userList.joinToString { user -> "Mac: ${user.mac}, id: ${user.id}" }}"
//            )
//        }

        // floating action button navigation logic
        val fab: FloatingActionButton = root.findViewById(R.id.floatingActionButton)
        fab.setOnClickListener {
            it.findNavController().navigate(R.id.action_navigation_signal_strength_list_to_navigation_user_add)
        }

        //val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}