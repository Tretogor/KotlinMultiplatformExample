package com.example.android.kotlinmultiplatform.views.input

import androidx.lifecycle.ViewModel
import com.jetbrains.handson.mpp.mobile.model.InputRow
import com.jetbrains.handson.mpp.mobile.state.ui.GetViewInformationState
import com.jetbrains.handson.mpp.mobile.state.ui.SendViewInformation
import com.jetbrains.handson.mpp.mobile.store.Store

class PocInputViewModel constructor(
    private val store: Store
) : ViewModel() {

    private lateinit var row: InputRow

    init {
        store.subscribe {
            if (it is GetViewInformationState && it.id == row.id) {
                store.dispatch(SendViewInformation("query"))
            }
        }
    }

    fun setRow(row: InputRow) {
        this.row = row
    }

    override fun onCleared() {
        super.onCleared()
    }

}