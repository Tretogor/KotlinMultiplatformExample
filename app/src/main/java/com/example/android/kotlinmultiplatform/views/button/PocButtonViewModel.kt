package com.example.android.kotlinmultiplatform.views.button

import androidx.lifecycle.ViewModel
import com.jetbrains.handson.mpp.mobile.model.ButtonRow
import com.jetbrains.handson.mpp.mobile.store.Store

class PocButtonViewModel constructor(
    private val store: Store
) : ViewModel() {

    private lateinit var row: ButtonRow

    init {
        store.subscribe {

        }
    }

    fun onButtonClick() {
        store.dispatch(row.action)
    }

    fun setRow(row: ButtonRow) {
        this.row = row
    }

    override fun onCleared() {
        super.onCleared()
    }
}