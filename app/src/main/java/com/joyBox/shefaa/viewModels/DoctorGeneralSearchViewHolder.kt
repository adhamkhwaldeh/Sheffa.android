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
import com.joyBox.shefaa.adapters.SpecializationSpinnerAdapter
import com.joyBox.shefaa.entities.SpecialistAutoComplete
import com.joyBox.shefaa.filtrations.DoctorFilter

class DoctorGeneralSearchViewHolder : RecyclerView.ViewHolder {

    @BindView(R.id.doctorName)
    lateinit var doctorName: EditText

    @BindView(R.id.specializationSpinner)
    lateinit var specializationSpinner: Spinner

    @BindView(R.id.costEditText)
    lateinit var costEditText: EditText

    @BindView(R.id.cityTextView)
    lateinit var cityTextView: TextView

    var context: Context

    constructor(itemView: View) : super(itemView) {
        ButterKnife.bind(this, itemView)
        context = itemView.context
    }

    fun getDoctorSearchModel(): DoctorFilter {
        var specialistAutoComplete: SpecialistAutoComplete? = null
        if (specializationSpinner.adapter != null) {
            val adapter = (specializationSpinner.adapter as SpecializationSpinnerAdapter)
            specialistAutoComplete = adapter.specList[specializationSpinner.selectedItemPosition]
            if (specialistAutoComplete.tid.isNullOrBlank())
                specialistAutoComplete = null
        }

        return DoctorFilter(query = doctorName.text.toString(),
                specialistAutoComplete = specialistAutoComplete, cost = costEditText.text.toString()
                , city = cityTextView.text.toString())
    }


}