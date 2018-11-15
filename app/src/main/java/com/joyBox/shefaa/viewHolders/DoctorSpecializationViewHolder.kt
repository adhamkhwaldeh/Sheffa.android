package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.SpecialistAutoComplete

class DoctorSpecializationViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {
    var context: Context

    @BindView(R.id.specializationName)
    lateinit var specializationName: TextView

    @BindView(R.id.selectedItemCheckBox)
    lateinit var selectedItemCheckBox: CheckBox

    init {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(specialistAutoComplete: SpecialistAutoComplete) {
        specializationName.text = specialistAutoComplete.name
        selectedItemCheckBox.isChecked = specialistAutoComplete.selected
    }

}