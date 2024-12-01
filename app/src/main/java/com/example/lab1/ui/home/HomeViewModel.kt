package com.example.lab1.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab1.data.Matavimas
import com.example.lab1.data.UserDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : ViewModel() {

    private val _sigList = MutableLiveData<List<Matavimas>>()
    val sigList: LiveData<List<Matavimas>> get() = _sigList

    private val _sigList2 = MutableLiveData<List<Matavimas>>()
    val sigList2: LiveData<List<Matavimas>> get() = _sigList2

    private val MatavimaiDao = UserDatabase.getDatabase(application).matavimaiDao()

    var allSignals: Flow<List<Matavimas>> = MatavimaiDao.getAllMeasurements()
    fun updateSignals(){
        allSignals = MatavimaiDao.getAllMeasurements()
        viewModelScope.launch(Dispatchers.IO) {
            MatavimaiDao.getAllMeasurements().collect { list ->
                _sigList.postValue(list)
            }
        }
    }
}