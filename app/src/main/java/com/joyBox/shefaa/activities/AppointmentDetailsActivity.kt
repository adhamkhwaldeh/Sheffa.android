package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R


class AppointmentDetailsActivity : BaseActivity() {

    companion object {
        const val AppointmentDetailsActivity_Appointment_Tag = "AppointmentDetailsActivity_Appointment_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    private fun initToolBar() {
        toolbar.setTitle(R.string.AppointmentDetail)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appointment_details_layout)
        ButterKnife.bind(this)
        initToolBar()
    }

}