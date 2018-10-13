package com.joyBox.shefaa.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorAutoComplete
import android.R.string.no
import android.databinding.adapters.TextViewBindingAdapter.setText
import android.R.attr.label
import android.annotation.SuppressLint
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView


/**
 * Created by Adhamkh on 2018-10-01.
 */
class DoctorAutoCompleteListViewAdapter constructor(context: Context,
                                                    private val doctorAutoCompleteList: MutableList<DoctorAutoComplete>) :
        ArrayAdapter<DoctorAutoComplete>(context, R.layout.doctor_autocomplete_item, doctorAutoCompleteList) {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.doctor_autocomplete_item, parent, false)

        val textView = rowView.findViewById(R.id.doctorName) as TextView
        textView.text = (doctorAutoCompleteList[position].nothing)

        return rowView
    }

    //    fun getView(position: Int, convertView: View, parent: ViewGroup): View {
//        val inflater = context
//                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//        val rowView = inflater.inflate(R.layout.rowlayout, parent, false)
//        val textView = rowView.findViewById(R.id.label) as TextView
//        val imageView = rowView.findViewById(R.id.icon) as ImageView
//        textView.setText(values[position])
//        // change the icon for Windows and iPhone
//        val s = values[position]
//        if (s.startsWith("iPhone")) {
//            imageView.setImageResource(R.drawable.no)
//        } else {
//            imageView.setImageResource(R.drawable.ok)
//        }
//
//        return rowView
//    }

}