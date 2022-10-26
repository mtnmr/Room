package com.example.roomsample.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsample.room.Application
import com.example.roomsample.room.SampleEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TextViewModel:ViewModel() {
    private val _textData = MutableLiveData<String>()
    val textData :LiveData<String> = _textData

    val editText: MutableLiveData<String> = MutableLiveData<String>("")

    fun changeText(){
        _textData.value = editText.value.toString()
        saveSampleData(textData.value.toString())
        editText.value = ""
    }

    //ここからroom, coroutine
    private val _dataList: MutableLiveData<List<SampleEntity>> = MutableLiveData<List<SampleEntity>>()
    val dataList:LiveData<List<SampleEntity>> = _dataList

    private val dao = Application.database.sampleDao()

    init {
        loadSampleDataList()
    }

    private fun loadSampleDataList(){
        viewModelScope.launch{
            _dataList.value = dao.loadAllData()
        }
    }

    private fun saveSampleData(text:String){
        viewModelScope.launch {
            if(text != ""){
                dao.saveData(SampleEntity(description = text))
            }
            loadSampleDataList()
        }
    }
}