package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity

class DoctorAddPrescriptionActivity : BaseActivity() {

    companion object {
        const val DoctorAddPrescriptionActivity_Tag = "DoctorAddPrescriptionActivity_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_add_prescription_layout)
        ButterKnife.bind(this)
    }


}