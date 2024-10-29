package com.example.lab1.ui.signalstrengthlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignalStrengthListViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Signal strength Fragment"
    }
    val text: LiveData<String> = _text
}