package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Prescription

class PrescriptionInfoViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var context: Context = view.context

    @BindView(R.id.patientName)
    lateinit var patientName: TextView

    @BindView(R.id.doctorName)
    lateinit var doctorName: TextView

    @BindView(R.id.diagnosisName)
    lateinit var diagnosisName: TextView

    @BindView(R.id.dateText)
    lateinit var dateText: TextView

    @BindView(R.id.testsName)
    lateinit var testsName: TextView

    @BindView(R.id.codeText)
    lateinit var codeText: TextView

    @BindView(R.id.keyText)
    lateinit var keyText: TextView


    init {
        ButterKnife.bind(this, view)
    }

    fun bind(prescription: Prescription) {
        patientName.text = prescription.Patient_Name
        doctorName.text = prescription.author_Name
        diagnosisName.text = prescription.diagnosisAsString
        dateText.text = prescription.prec_date
        testsName.text = prescription.title
        codeText.text = prescription.Prescription_code
        keyText.text = prescription.Prescription_key
    }

}