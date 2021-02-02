package com.example.android.kotlinmultiplatform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.android.kotlinmultiplatform.views.button.PocButtonView
import com.example.android.kotlinmultiplatform.views.input.PocInputView
import com.jetbrains.handson.mpp.mobile.createApplicationScreenMessage
import com.jetbrains.handson.mpp.mobile.model.ButtonRow
import com.jetbrains.handson.mpp.mobile.model.InputRow
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.liveData.observe(this, Observer {
            it.forEach { row ->
                when(row) {
                    is ButtonRow -> {
                        root.addView(
                            PocButtonView(this).apply { setRow(row) }
                        )
                    }
                    is InputRow -> {
                        root.addView(
                            PocInputView(this).apply { setRow(row) }
                        )
                    }
                }
            }
        })
        viewModel.getRows()
    }
}