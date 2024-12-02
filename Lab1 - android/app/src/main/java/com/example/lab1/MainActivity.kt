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
import com.example.lab1.data.ApiClient
import com.example.lab1.data.Matavimas
import com.example.lab1.data.Stiprumas
import com.example.lab1.data.User
import com.example.lab1.data.UserDatabase
import com.example.lab1.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    suspend fun ReadUsersFromApi(): List<User>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiClient.apiService.getUsers().awaitResponse()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
    suspend fun ReadStrengthsFromApi(): List<Stiprumas>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiClient.apiService.getStiprumai().awaitResponse()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }
    suspend fun ReadMatavimaiFromApi(): List<Matavimas>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = ApiClient.apiService.getMatavimai().awaitResponse()
                if (response.isSuccessful) {
                    response.body()
                } else {
                    null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

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
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_signal_strength_list,R.id.navigation_user_edit
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

    }

    override fun onStart() {
        super.onStart()
        val userDao = UserDatabase.getDatabase(applicationContext).userDao()
        val stiprumaiDao = UserDatabase.getDatabase(applicationContext).stiprumaiDao()
        val matavimaiDao = UserDatabase.getDatabase(applicationContext).matavimaiDao()
        //load data from api
        lifecycleScope.launch {
            val users = ReadUsersFromApi()
            if (users != null) {
                users.forEach {user ->
                    userDao.insert(user)
                }
            } else {
                Log.d("USERS API","Error: Could not fetch users from the API")

            }
        }
        lifecycleScope.launch {
            val users = ReadMatavimaiFromApi()
            if (users != null) {
                users.forEach {matavimas ->
                    matavimaiDao.insert(matavimas)
                }
            } else {
                Log.d("MATAVIMAI API","Error: Could not fetch matavimai from the API")
            }
        }
        lifecycleScope.launch {
            val users = ReadStrengthsFromApi()
            if (users != null) {
                users.forEach {strength ->
                    stiprumaiDao.insert(strength)
                }
            } else {
                Log.d("STIPRUMAI API","Error: Could not fetch stiprumai from the API")

            }
        }
        lifecycleScope.launch {
            userDao.getAllUsers().collectLatest { users ->
                if (users.isNotEmpty()) {
                    users.forEach { user ->
                        Log.d("DATABASE", "User: ID=${user.id}, MAC=${user.mac}, Sensor=${user.sensorius}, Strength=${user.stiprumas}")
                    }
                } else {
                    Log.d("DATABASE", "No users found in the preloaded database.")
                }
            }
        }
    }

}