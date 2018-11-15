package com.joyBox.shefaa.activities

import android.os.Bundle
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R

class LabDetailsActivity : BaseActivity() {
    companion object {
        const val LabDetailsActivity_Tag = "LabDetailsActivity_Tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lab_details_layout)
        ButterKnife.bind(this)
    }
}