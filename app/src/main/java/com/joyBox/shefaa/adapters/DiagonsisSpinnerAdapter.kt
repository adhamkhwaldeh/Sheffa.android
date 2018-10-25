package com.joyBox.shefaa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DiagnosiseAutoComplete

/**
 * Created by Adhamkh on 2018-10-25.
 */
class DiagonsisSpinnerAdapter(val context: Context, val diagnosiseAutoCompleteList: MutableList<DiagnosiseAutoComplete>) : BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        if (convertView == null) {
            view = mInflater.inflate(R.layout.specialization_item, parent, false)
        } else {
            view = convertView
        }

        val specializationName: TextView = view.findViewById(R.id.specializationName)
        specializationName.text = diagnosiseAutoCompleteList[position].name
        // setting adapter item height programatically.

//        val params = view.layoutParams
//        params.height = 60
//        view.layoutParams = params
        return view
    }

    override fun getItem(position: Int): Any {
        return diagnosiseAutoCompleteList[position]
    }

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = diagnosiseAutoCompleteList.size

    fun setSelectedDiagnosis(diagnosis: String): Int {
        val sz = diagnosiseAutoCompleteList.size - 1
        for (i in 0..sz) {
            if (diagnosiseAutoCompleteList.get(i).name.equals(diagnosis, false)) {
                return i
            }
        }
        return -1
    }
}


