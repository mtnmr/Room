package com.example.roomsample.viewmodel

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.roomsample.room.Application
import com.example.roomsample.room.SampleDao
import com.example.roomsample.room.SampleEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TextViewModel:ViewModel() {
    private val _textData = MutableLiveData<String>()

    val textData :LiveData<String> = _textData

    val editText: MutableLiveData<String> = MutableLiveData<String>("")

    fun changeText(){
        _textData.value = editText.value.toString()
//        saveSampleData(DataModel(textData.value.toString()))
        editText.value = ""
    }


    //ここからroom, coroutine
    private val _dataList: MutableLiveData<List<DataModel>> =
        MutableLiveData<List<DataModel>>().also { mutableLiveData ->
            mutableLiveData.value = emptyList()
        }

    val dataList:LiveData<List<DataModel>> = _dataList

    private val dao = Application.database.sampleDao()

    fun loadSampleDataList(){
        val dataMutableList = mutableListOf<DataModel>()
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default){
                dao.loadAllData().forEach{data ->
                    dataMutableList.add(DataModel(description = data.description))
                }
            }

            _dataList.value = dataMutableList
        }
    }

    fun saveSampleData(data:DataModel){
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.Default){
                dao.saveData(SampleEntity(description = data.description))
            }

//            loadSampleDataList()
        }
    }


}