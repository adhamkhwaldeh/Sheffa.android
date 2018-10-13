package com.joyBox.shefaa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.JoyBox.Shefaa.R


class SpecializationSpinnerAdapter(val context: Context, val specList: MutableList<String>) : BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            view = mInflater.inflate(R.layout.specialization_item, parent, false)
        } else {
            view = convertView
        }

        val specializationName: TextView = view.findViewById(R.id.specializationName)
        specializationName.text = specList[position]
        // setting adapter item height programatically.

//        val params = view.layoutParams
//        params.height = 60
//        view.layoutParams = params
        return view
    }

    override fun getItem(position: Int): Any {
        return specList[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = specList.size

}