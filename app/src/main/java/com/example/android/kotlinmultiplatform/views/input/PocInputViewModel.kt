package com.example.android.kotlinmultiplatform.views.input

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wcisang.kotlinmultiplatform.model.InputRow
import com.wcisang.kotlinmultiplatform.state.ui.GetViewInformationState
import com.wcisang.kotlinmultiplatform.state.ui.InvalidInputState
import com.wcisang.kotlinmultiplatform.state.ui.LoadingState
import com.wcisang.kotlinmultiplatform.state.ui.SendViewInformation
import com.wcisang.kotlinmultiplatform.store.Store

class PocInputViewModel constructor(
    private val store: Store
) : ViewModel() {

    private lateinit var row: InputRow
    private var input : String = ""
    var liveData = MutableLiveData<String?>()

    init {
        store.subscribe {
            if (it is GetViewInformationState && it.id == row.id) {
                store.dispatch(SendViewInformation(row.id, input, row.validation))
            }else if (it is InvalidInputState && it.id == row.id) {
                liveData.value = it.message
            }else if (it is LoadingState) {
                liveData.value = "carregando"
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