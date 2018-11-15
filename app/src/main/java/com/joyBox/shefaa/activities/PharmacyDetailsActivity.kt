package com.joyBox.shefaa.activities

import android.os.Bundle
import com.JoyBox.Shefaa.R

class PharmacyDetailsActivity : BaseActivity() {
    companion object {
        const val PharmacyDetailsActivity_Tag = "PharmacyDetailsActivity_Tag"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pharmacy_details_layout)
    }


}