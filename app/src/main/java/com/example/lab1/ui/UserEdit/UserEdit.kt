package com.example.lab1.ui.UserEdit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.lab1.R
import com.example.lab1.data.User
import kotlinx.coroutines.launch

class UserEdit : Fragment() {


    private lateinit var editViewModel: UserEditViewModel
    private var mac: String? = null
    private var id1: Int? = null
    private var id2: Int? = null
    private var id3: Int? = null
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_user_edit, container, false)
        editViewModel = UserEditViewModel(requireActivity().application)
        // Initialize userDao


        mac = arguments?.getString("mac") // Receive ID from Bundle
        Log.d("yeeeeeeeeet", mac.toString())
        // Load user data if userId is available

        mac?.let { id ->
            editViewModel.GetUser(id) // Call to load user data
        }

        editViewModel.userList.observe(viewLifecycleOwner) { listOfUsers ->
            listOfUsers?.let {
                // Populate UI elements with user's data
                id1 = listOfUsers[0].id
                id2 = listOfUsers[1].id
                id3 = listOfUsers[2].id
                view.findViewById<EditText>(R.id.editMac).setText(listOfUsers[0].mac)

                view.findViewById<EditText>(R.id.editSensorius).setText(listOfUsers[0].sensorius)
                view.findViewById<EditText>(R.id.editStiprumas1).setText(listOfUsers[0].stiprumas)

                view.findViewById<EditText>(R.id.editSensorius2).setText(listOfUsers[1].sensorius)
                view.findViewById<EditText>(R.id.editStiprumas2).setText(listOfUsers[1].stiprumas)

                view.findViewById<EditText>(R.id.editStiprumas3).setText(listOfUsers[2].stiprumas)
                view.findViewById<EditText>(R.id.editSensorius3).setText(listOfUsers[2].sensorius)
            }
        }


        // Set up save button click listener
        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            saveChanges()
        }

        return view
    }

    private fun saveChanges() {
        lifecycleScope.launch {

            if (id1 == null)
            {
                id1 = 0
                id2 = 0
                id3 = 0
            }
            val mac = view?.findViewById<EditText>(R.id.editMac)?.text.toString()
            val sensorius = view?.findViewById<EditText>(R.id.editSensorius)?.text.toString()
            val sensorius2 = view?.findViewById<EditText>(R.id.editSensorius2)?.text.toString()
            val sensorius3 = view?.findViewById<EditText>(R.id.editSensorius3)?.text.toString()
            val stiprumas = view?.findViewById<EditText>(R.id.editStiprumas1)?.text.toString()
            val stiprumas2 = view?.findViewById<EditText>(R.id.editStiprumas2)?.text.toString()
            val stiprumas3 = view?.findViewById<EditText>(R.id.editStiprumas3)?.text.toString()

            val u1 = User(id1!!,mac, stiprumas, sensorius)
            val u2 = User(id2!!,mac, stiprumas2,sensorius2)
            val u3 = User(id3!!,mac, stiprumas3,sensorius3)
            editViewModel.UpdateUser(u1)
            editViewModel.UpdateUser(u2)
            editViewModel.UpdateUser(u3)

            findNavController().popBackStack()  // Return to the previous screen
        }
    }
}