package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.helpers.IntentHelper

class DoctorDashBoardActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_dashboard_layout)
        ButterKnife.bind(this)
    }

    @OnClick(R.id.MyClinicContainer)
    fun onMyClinicContainerClick(view: View) {
        IntentHelper.startDoctorProfileActivity(this@DoctorDashBoardActivity)
    }

    @OnClick(R.id.ManageAppointmentsContainer)
    fun onManageAppointmentsContainer(view: View) {
        IntentHelper.startDoctorAppointmentActivity(this@DoctorDashBoardActivity)
    }


}