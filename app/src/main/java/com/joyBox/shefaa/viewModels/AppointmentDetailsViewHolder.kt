package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.AppointmentEntity

class AppointmentDetailsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.Appointment_Id)
    lateinit var Appointment_Id: TextView

    @BindView(R.id.Title)
    lateinit var Title: TextView

    @BindView(R.id.patientname)
    lateinit var patientname: TextView

    @BindView(R.id.UrgentAppointmentCause)
    lateinit var UrgentAppointmentCause: TextView

    @BindView(R.id.Appointment_Status)
    lateinit var Appointment_Status: TextView

    @BindView(R.id.cancelBtn)
    lateinit var cancelBtn: Button

    @BindView(R.id.Acceptappointmentbtn)
    lateinit var Acceptappointmentbtn: Button

    @BindView(R.id.Appointment_Time)
    lateinit var Appointment_Time: TextView

    @BindView(R.id.Appointment_Date)
    lateinit var Appointment_Date: TextView

    @BindView(R.id.Editdatebtn)
    lateinit var Editdatebtn: ImageView

    @BindView(R.id.EditTimebtn)
    lateinit var EditTimebtn: ImageView

    @BindView(R.id.UrgentAppointmentCausetext)
    lateinit var UrgentAppointmentCausetext: TextView

    @BindView(R.id.div1)
    lateinit var div1: View

    @BindView(R.id.div2)
    lateinit var div2: View

    @BindView(R.id.Appointment_Statustext)
    lateinit var Appointment_Statustext: TextView

    lateinit var appointment: AppointmentEntity
    val context: Context

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(appointment: AppointmentEntity) {
        this.appointment = appointment
        Appointment_Id.setText(appointment.nid);
        Title.setText(appointment.getTitle());
        patientname.setText(appointment.getPatient_Name());
        UrgentAppointmentCause.setText(appointment.getUrgent_appointment_cause());

        if (appointment.getUrgent_appointment().equals("yes")) {
            Acceptappointmentbtn.setVisibility(View.VISIBLE);
        } else {
            UrgentAppointmentCausetext.setVisibility(View.GONE);
            UrgentAppointmentCause.setVisibility(View.GONE);
            div1.setVisibility(View.GONE);

            Appointment_Statustext.setVisibility(View.GONE);
            Appointment_Status.setVisibility(View.GONE);
            div2.setVisibility(View.GONE);

            Acceptappointmentbtn.setVisibility(View.GONE);
        }
        if (appointment.getApproved().equals("0")) {
            Appointment_Status.setText(context.resources.getString(R.string.NotApproved));
        } else {
            Appointment_Status.setText(context.resources.getString(R.string.Approved));
            Acceptappointmentbtn.setVisibility(View.GONE);
        }
        Appointment_Time.setText(appointment.getAppointment_start_time());
        Appointment_Date.setText(appointment.getAppointment_Date());

    }
}