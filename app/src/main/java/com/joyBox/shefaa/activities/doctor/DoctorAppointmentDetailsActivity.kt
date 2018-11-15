package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import android.view.View
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.viewHolders.DoctorAppointmentDetailsViewHolder


class DoctorAppointmentDetailsActivity : BaseActivity() {

    companion object {
        const val DoctorAppointmentDetailsActivity_Tag = "DoctorAppointmentDetailsActivity_Tag"
    }

    lateinit var doctorAppointment: DoctorAppointment
    lateinit var doctorAppointmentDetailsViewHolder: DoctorAppointmentDetailsViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_appointment_details_layout)
        ButterKnife.bind(this)
        val jSon = intent.getStringExtra(DoctorAppointmentDetailsActivity_Tag)
        doctorAppointment = Gson().fromJson(jSon, DoctorAppointment::class.java)
        doctorAppointmentDetailsViewHolder = DoctorAppointmentDetailsViewHolder(findViewById(android.R.id.content))

    }

    @OnClick(R.id.startAppointmentBtn)
    fun onStartAppointmentButtonClick(view: View) {

    }

    @OnClick(R.id.endAppointmentBtn)
    fun onEndAppointmentButtonClick(view: View) {

    }

    @OnClick(R.id.acceptAppointmentBtn)
    fun onAcceptAppointmentButtonClick(view: View) {

    }

    @OnClick(R.id.refuseAppointmentBtn)
    fun onRefuseAppointmentButtonClick(view: View) {

    }

    @OnClick(R.id.addPrescriptionBtn)
    fun onAddPrescriptionButtonClick(view: View) {

    }

}