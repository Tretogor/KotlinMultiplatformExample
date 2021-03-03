package com.example.android.kotlinmultiplatform.views.input

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.core.widget.addTextChangedListener
import com.example.android.kotlinmultiplatform.R
import com.wcisang.kotlinmultiplatform.model.InputRow
import kotlinx.android.synthetic.main.input_view.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class PocInputView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), KoinComponent {

    private val viewModel by inject<PocInputViewModel>()

    init {
        inflate(context, R.layout.input_view, this)
        viewModel.liveData.observeForever {
            edtInput.error = it
        }
    }

    fun setRow(row: InputRow) {
        viewModel.setRow(row)
        setView(row)
        setListener()
    }

    private fun setView(row: InputRow) {
        setHint(row.data.hint)
    }

    private fun setHint(hint : CharSequence) {
        edtInput.hint = hint
    }

    private fun setListener() {
        edtInput.addTextChangedListener {
            viewModel.setInput(it.toString())
        }
    }
}