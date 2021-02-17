package com.example.android.kotlinmultiplatform.views.input

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jetbrains.handson.mpp.mobile.model.InputRow
import com.jetbrains.handson.mpp.mobile.state.ui.GetViewInformationState
import com.jetbrains.handson.mpp.mobile.state.ui.InvalidInputState
import com.jetbrains.handson.mpp.mobile.state.ui.SendViewInformation
import com.jetbrains.handson.mpp.mobile.store.Store

class PocInputViewModel constructor(
    private val store: Store
) : ViewModel() {

    private lateinit var row: InputRow
    private var input : String = ""
    var liveData = MutableLiveData<String?>()

    init {
        store.subscribe {
            if (it is GetViewInformationState && it.id == row.id) {
                store.dispatch(SendViewInformation(input, row.validation))
            }else if (it is InvalidInputState) {
                liveData.value = it.message
            }else {

            }
        }
    }

    fun setRow(row: InputRow) {
        this.row = row
    }

    fun setInput(input: String) {
        this.input = input
    }

    override fun onCleared() {
        super.onCleared()
    }

}