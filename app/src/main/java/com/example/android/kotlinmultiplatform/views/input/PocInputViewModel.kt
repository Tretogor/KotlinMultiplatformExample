package com.example.android.kotlinmultiplatform.views.input

import androidx.lifecycle.ViewModel
import com.jetbrains.handson.mpp.mobile.model.InputRow

class PocInputViewModel constructor(
) : ViewModel() {

    private lateinit var row: InputRow

    init {
    }

    fun setRow(row: InputRow) {
        this.row = row
    }

    override fun onCleared() {
        super.onCleared()
    }

}