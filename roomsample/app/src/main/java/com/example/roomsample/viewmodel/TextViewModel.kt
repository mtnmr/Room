package com.example.roomsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TextViewModel:ViewModel() {
    private val _textData = MutableLiveData<String>()

    val textData :LiveData<String> = _textData

    val editText: MutableLiveData<String> = MutableLiveData<String>("")

    fun changeText(){
        _textData.value = editText.value.toString()
        editText.value = ""
    }

}