package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.entities.AppointmentAutoComplete
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.fragments.doctorBudgetFragments.DoctorIncomingFragment


class DoctorAppointmentInvoiceActivity : BaseActivity() {

    companion object {
        const val DoctorAppointmentInvoiceActivity_Tag = "DoctorAppointmentInvoiceActivity_Tag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_appointment_invoice_layout)
        ButterKnife.bind(this)
        val jSon=intent.getStringExtra(DoctorAppointmentInvoiceActivity_Tag)
        val doctorAppointment=Gson().fromJson(jSon,DoctorAppointment::class.java)
        supportFragmentManager.beginTransaction().replace(R.id.container,
                DoctorIncomingFragment.getNewInstance(doctorAppointment)).commit()
    }

}