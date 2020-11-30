package com.example.android.kotlinmultiplatform.views.button

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.example.android.kotlinmultiplatform.R
import com.jetbrains.handson.mpp.mobile.model.ButtonRow
import kotlinx.android.synthetic.main.button_view.view.*
import org.koin.core.KoinComponent
import org.koin.core.inject

class PocButtonView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), KoinComponent{

    private val viewModel by inject<PocButtonViewModel>()

    init {
        inflate(context, R.layout.button_view, this)
    }

    fun setRow(row: ButtonRow) {
        viewModel.setRow(row)
        setView(row)
    }

    private fun setView(row: ButtonRow) {
        btView.run {
            text = row.data.text
            setOnClickListener { viewModel.onButtonClick() }
        }
    }
}