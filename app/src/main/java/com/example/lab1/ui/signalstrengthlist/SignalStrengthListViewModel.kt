package com.example.lab1.ui.signalstrengthlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab1.data.ReadUsers
import com.example.lab1.data.ReadUsersFromApi
import com.example.lab1.data.User
import com.example.lab1.data.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignalStrengthListViewModel(application: Application) : AndroidViewModel(application ) {

    private val userDao = UserDatabase.getDatabase(application).userDao()
    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList
    private fun loadUsers()
    {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.getAllUsers().collect() {userList ->
                _userList.postValue(userList)
            }
        }
    }
    init {
        loadUsers()
    }

    fun loadUsersFromJson() {
        viewModelScope.launch(Dispatchers.IO)
        {
            val users: List<User> = ReadUsers(getApplication())

            users.forEach {user ->
                userDao.insert(user)
            }

            loadUsers()
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(user)

            loadUsers()
        }
    }

    fun clearUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.deleteAllUsers()
            loadUsers()
        }
    }
    private val _text = MutableLiveData<String>().apply {
        value = "This is Signal strength Fragment"
    }
    val text: LiveData<String> = _text
}