package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.MeasurementType
import com.joyBox.shefaa.entities.models.SelfMonitorAddModel


class SelfMonitorAddViewHolder constructor(val view: View) : RecyclerView.ViewHolder(view) {
    val context: Context = view.context

    @BindView(R.id.measureTypeSpinner)
    lateinit var measureTypeSpinner: Spinner

    @BindView(R.id.measureValue)
    lateinit var measureValue: EditText

    @BindView(R.id.measureUnit)
    lateinit var measureUnit: EditText

    @BindView(R.id.measureDate)
    lateinit var measureDate: EditText

    @BindView(R.id.measureTime)
    lateinit var measureTime: EditText

    @BindView(R.id.measureNotes)
    lateinit var measureNotes: EditText

    init {
        ButterKnife.bind(this, view)
    }

    fun isValid(): Boolean {


        return true
    }

    fun getSelfMonitorAddModel(): SelfMonitorAddModel {

        val indicatorType: String = (measureTypeSpinner.selectedItem as MeasurementType).name
        return SelfMonitorAddModel(notes = measureNotes.text.toString(),
                indicatorValue = measureValue.text.toString(), date = measureDate.text.toString(),
                time = measureTime.text.toString(), indicatorType = indicatorType,
                indicatorUnit = measureUnit.text.toString())
    }


}