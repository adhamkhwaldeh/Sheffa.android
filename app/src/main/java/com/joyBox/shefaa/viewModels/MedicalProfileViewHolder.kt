package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.MedicalProfile
import com.joyBox.shefaa.networking.NetworkingHelper

class MedicalProfileViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    var context: Context = view.context

    @BindView(R.id.ageTextView)
    lateinit var ageTextView: TextView

    @BindView(R.id.heightTextView)
    lateinit var heightTextView: TextView

    @BindView(R.id.weightTextView)
    lateinit var weightTextView: TextView

    @BindView(R.id.smokeCheckBox)
    lateinit var smokeCheckBox: CheckBox

    @BindView(R.id.allergiesCheckBox)
    lateinit var allergiesCheckBox: CheckBox

    @BindView(R.id.diagnosisHeadAche)
    lateinit var diagnosisHeadAche: TextView

    @BindView(R.id.emergencyContactTextView)
    lateinit var emergencyContactTextView: TextView

    @BindView(R.id.pregnantCheckBox)
    lateinit var pregnantCheckBox: CheckBox

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(medicalProfile: MedicalProfile) {
        ageTextView.text = ""
        heightTextView.text = medicalProfile.field_height
        weightTextView.text = medicalProfile.field_weight

        if (medicalProfile.field_do_you_smoke.equals("0", true)) {
            smokeCheckBox.isChecked = true
        }
        if (medicalProfile.field_do_you_have_latex_or_any_allergies.equals("0", false)) {
            allergiesCheckBox.isChecked = true
        }
        diagnosisHeadAche.text = medicalProfile.field_diagnosis_of_disease
        emergencyContactTextView.text = medicalProfile.field_emergency_contact

        if (medicalProfile.field_women_only_pregnant.equals("0", false)) {
            pregnantCheckBox.isChecked = true
        }
    }

    fun getUpdateUrl(userId: String): String {
        val isSmoke = (if (smokeCheckBox.isChecked) "1" else "0")
        val isAllergies = (if (allergiesCheckBox.isChecked) "1" else "0")
        val isPregnant = (if (pregnantCheckBox.isChecked) "1" else "0")
        return NetworkingHelper.GeneralProfile_Update_Url + "?uid=" + userId +
                "&type=patient&emergency_contact=" + emergencyContactTextView.text.toString() +
                "&weight=" + weightTextView.text.toString() + "&height=" + heightTextView.text.toString() +
                "&disease=" + diagnosisHeadAche.text.toString() +
                "&smoke=" + isSmoke + "&allergies=" + isAllergies + "&pregnant=" + isPregnant
    }


}