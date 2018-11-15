package com.joyBox.shefaa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.MeasurementType


class MeasurementTypeSpinnerAdapter(val context: Context, val measurementTypeList: MutableList<MeasurementType>)
    : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            view = mInflater.inflate(R.layout.measurement_type_item, parent, false)
        } else {
            view = convertView
        }

        val measureTypeName: TextView = view.findViewById(R.id.measureTypeName)
        measureTypeName.text = measurementTypeList[position].name
        // setting adapter item height programatically.

//        val params = view.layoutParams
//        params.height = 60
//        view.layoutParams = params
        return view
    }

    override fun getItem(position: Int): Any {
        return measurementTypeList[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = measurementTypeList.size

}