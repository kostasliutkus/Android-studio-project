package com.example.lab1.ui.signalstrengthlist

import android.content.Context
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.lab1.R
import com.example.lab1.databinding.FragmentNotificationsBinding
import com.example.lab1.databinding.FragmentSignalStrengthListBinding
import com.example.lab1.ui.notifications.NotificationsViewModel
import com.example.lab1.data.ReadStiprumai
import com.example.lab1.data.ReadUsers
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

class Signal_strength_list : Fragment() {

    private var _binding: FragmentSignalStrengthListBinding? = null
    private lateinit var sensorDataAdapter: SensorDataAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val signalStrengthListViewModel =
            ViewModelProvider(this).get(SignalStrengthListViewModel::class.java)

        _binding = FragmentSignalStrengthListBinding.inflate(inflater, container, false)


        val textView: TextView = binding.textSignalstrengthlist
        signalStrengthListViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        // Call ReadStiprumai with the context and handle potential exceptions
        val usersList = try {
            ReadUsers(requireContext())
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList() // Fallback to empty list in case of error
        }

       var recyclerView = binding.signalStrengthListRecyclerView

        recyclerView.layoutManager = LinearLayoutManager(context)
        sensorDataAdapter = SensorDataAdapter(usersList)
        recyclerView.adapter=sensorDataAdapter

        val root: View = binding.root
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}