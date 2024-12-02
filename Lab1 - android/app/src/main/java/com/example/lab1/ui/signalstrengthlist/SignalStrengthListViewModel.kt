package com.example.lab1.ui.signalstrengthlist

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab1.data.User
import com.example.lab1.data.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignalStrengthListViewModel(application: Application) : AndroidViewModel(application ) {

    private val userDao = UserDatabase.getDatabase(application).userDao()
    private val _userList = MutableLiveData<List<String>>()

    private val _userSaved = MutableLiveData<Boolean>()
    val userSaved: LiveData<Boolean> get() = _userSaved

    val userList: LiveData<List<String>> get() = _userList
    private fun loadUsers()
    {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.getUniqueUsers().collect() {userList ->
                _userList.postValue(userList)
            }
        }
    }
    init {
        loadUsers()
    }

    fun saveUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.insert(user)

            loadUsers()
        }
    }

    suspend fun getSelected(): String {
        val mac_address = userDao.getLatestUser()
        return mac_address.toString()
    }

    fun update() {
        _userSaved.postValue(true)
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