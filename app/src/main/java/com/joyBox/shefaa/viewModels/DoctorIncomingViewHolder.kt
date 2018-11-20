package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.AppointmentAutoComplete
import com.joyBox.shefaa.entities.models.AppointmentInvoiceModel
import com.joyBox.shefaa.entities.models.IncomingAddModel

class DoctorIncomingViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

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

    var appointmentAutoComplete: AppointmentAutoComplete? = null

    init {
        ButterKnife.bind(this, view)
    }

    fun setAppointment(appointmentAutoComplete: AppointmentAutoComplete) {
        this.appointmentAutoComplete = appointmentAutoComplete
        appointmentName.text = appointmentAutoComplete.title
        appointmentId.text = appointmentAutoComplete.id
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
        if (model.appointmentTitle.isBlank()) {
            return false
        }
        return true
    }

    fun getAppointmentInvoiceModel(): IncomingAddModel {
        return IncomingAddModel(title = addressEditText.text.toString(), invoiceValue = costEditText.text.toString(),
                desc = notesEditText.text.toString(), appointmentTitle = appointmentName.text.toString())
    }

}