package com.example.android.kotlinmultiplatform

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.example.android.kotlinmultiplatform.views.button.PocButtonView
import com.example.android.kotlinmultiplatform.views.input.PocInputView
import com.chrynan.parcelable.android.getParcelableExtra
import com.chrynan.parcelable.android.putExtra
import com.wcisang.kotlinmultiplatform.model.ButtonRow
import com.wcisang.kotlinmultiplatform.model.InputRow
import com.wcisang.kotlinmultiplatform.model.Screen
import com.wcisang.kotlinmultiplatform.serializers.SerializerUtils
import kotlinx.android.synthetic.main.activity_main.*

class AuxActivity: FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val screen = intent.getParcelableExtra(SCREEN_KEY, SerializerUtils.parcelable) as Screen?

        screen?.rows?.forEach { row ->
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
    }

    companion object {
        const val SCREEN_KEY = "SCREEN_KEY"

        fun newInstance(context: Context, screen: Screen): Intent {
            val intent = Intent(context, AuxActivity::class.java)
            intent.putExtra(SCREEN_KEY, screen, SerializerUtils.parcelable)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            return intent
        }
    }
}