package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity

class DoctorProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_profile_layout)
        ButterKnife.bind(this)
    }

}