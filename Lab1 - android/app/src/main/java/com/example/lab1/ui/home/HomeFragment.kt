package com.example.lab1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.lab1.R
import com.example.lab1.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var HomeView: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        HomeView = HomeViewModel(requireActivity().application)
        return inflater.inflate(R.layout.fragment_home,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Use the view parameter to find the GridView
        val gridView = view.findViewById<GridView>(R.id.gridView)
        HomeView.updateSignals()
        lifecycleScope.launch {
//            HomeView.BOMBA()
        }
        HomeView.sigList.observe(viewLifecycleOwner, Observer { signals ->
            if (signals != null) {
                gridView.setSignals(signals)  // Assuming you have a custom method to set data
            }
        })
        HomeView.sigList2.observe(viewLifecycleOwner, Observer { signals ->
            if (signals != null) {
                gridView.setUsers(signals)  // Assuming you have a custom method to set data
            }
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}