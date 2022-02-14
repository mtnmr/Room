package com.example.roomsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel:ViewModel() {
    private val _textData = MutableLiveData<String>()

    val textData :LiveData<String> = _textData

    fun changeText(){
        _textData.value = "EditText"
    }

}