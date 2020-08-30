package com.example.android.kotlinmultiplatform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jetbrains.handson.mpp.mobile.data.CustomRepository
import com.jetbrains.handson.mpp.mobile.model.Screen
import kotlinx.coroutines.launch

class MainViewModel(private val repository: CustomRepository) : ViewModel() {

    private val liveData = MutableLiveData<Screen>()

    fun getRows() {
        viewModelScope.launch {
            val list = repository.getRows()
            liveData.value = list
        }
    }
}