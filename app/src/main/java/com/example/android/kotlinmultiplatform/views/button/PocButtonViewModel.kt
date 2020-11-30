package com.example.android.kotlinmultiplatform.views.button

import androidx.lifecycle.ViewModel
import com.jetbrains.handson.mpp.mobile.model.ButtonRow

class PocButtonViewModel constructor(
) : ViewModel() {

    private lateinit var row: ButtonRow

    init {

    }

    fun onButtonClick() {
    }

    fun setRow(row: ButtonRow) {
        this.row = row
    }

    override fun onCleared() {
        super.onCleared()
    }
}