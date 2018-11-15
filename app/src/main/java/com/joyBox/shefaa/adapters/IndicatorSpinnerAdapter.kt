package com.joyBox.shefaa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.JoyBox.Shefaa.R

class IndicatorSpinnerAdapter(val context: Context, val indicatorList: MutableList<String>) : BaseAdapter() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            view = mInflater.inflate(R.layout.indicator_item, parent, false)
        } else {
            view = convertView
        }

        val indicatorName: TextView = view.findViewById(R.id.indicatorName)
        indicatorName.text = indicatorList[position]
        // setting adapter item height programatically.

//        val params = view.layoutParams
//        params.height = 60
//        view.layoutParams = params
        return view
    }

    override fun getItem(position: Int): Any {
        return indicatorList[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = indicatorList.size

}