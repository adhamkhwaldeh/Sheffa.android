package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.entities.models.AppointmentInvoiceModel

class DoctorAppointmentInvoiceViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.addressEditText)
    lateinit var addressEditText: EditText

    @BindView(R.id.appointmentName)
    lateinit var appointmentName: TextView

    @BindView(R.id.costEditText)
    lateinit var costEditText: EditText

    @BindView(R.id.notesEditText)
    lateinit var notesEditText: EditText

    @BindView(R.id.appointmentId)
    lateinit var appointmentId: TextView

    val context: Context = view.context

    var doctorAppointment: DoctorAppointment? = null

    init {
        ButterKnife.bind(this, view)
    }

    fun setAppointment(doctorAppointment: DoctorAppointment) {
        this.doctorAppointment = doctorAppointment
        appointmentName.text = doctorAppointment.title
        appointmentId.text = doctorAppointment.nid
    }

    fun isValid(): Boolean {
        val model = getAppointmentInvoiceModel()
        if (model.title.isBlank()) {
            return false
        }
        if (model.invoiceValue.isBlank()) {
            return false
        }
        if (model.desc.isBlank()) {
            return false
        }
        if (model.appointmentName.isBlank()) {
            return false
        }
        return true
    }

    fun getAppointmentInvoiceModel(): AppointmentInvoiceModel {
        return AppointmentInvoiceModel(title = addressEditText.text.toString(), invoiceValue = costEditText.text.toString(),
                desc = notesEditText.text.toString(), appointmentName = appointmentName.text.toString())
    }

}
