package com.example.lab1

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.lab1.data.ReadUsersFromApi
import com.example.lab1.data.UserDatabase
import com.example.lab1.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }
        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_signal_strength_list
            )
        )




        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onStart() {
        super.onStart()
        val userDao = UserDatabase.getDatabase(applicationContext).userDao()

        //load data from api
        lifecycleScope.launch {
            userDao.deleteAllUsers()
            val users = ReadUsersFromApi()
            if (users != null) {
                 // Successfully retrieved users
                users.forEach {user ->
                    userDao.insert(user)
                }
            } else {
                // Failed to fetch users
                println("Error: Could not fetch users from the API")
            }
        }
    }

}