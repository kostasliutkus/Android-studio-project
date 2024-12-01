package com.example.lab1.ui.UserEdit

import androidx.lifecycle.ViewModel
import com.example.lab1.data.User
import com.example.lab1.data.UserDatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserEditViewModel(application: Application) : AndroidViewModel(application)  {



    private val userDao = UserDatabase.getDatabase(application).userDao()
    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList


    fun GetUser(mac: String) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.getUser(mac).collect { users ->
                _userList.postValue(users) // This should update the LiveData
            }
        }
    }
    fun UpdateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userDao.update(user)

        }
    }


}