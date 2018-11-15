package com.joyBox.shefaa.adapters

import android.content.Context
import android.widget.ArrayAdapter
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DoctorAutoComplete
import android.annotation.SuppressLint
import android.widget.TextView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.joyBox.shefaa.listeners.OnDoctorSelectListener
import java.util.*

class DoctorAutoCompleteListViewAdapter constructor(
        context: Context,
        private var doctorAutoCompleteList: MutableList<DoctorAutoComplete>
        , private val onDoctorSelectListener: OnDoctorSelectListener) :
        ArrayAdapter<DoctorAutoComplete>(context, R.layout.doctor_autocomplete_item,
                doctorAutoCompleteList), Filterable {

    private val originalList: MutableList<DoctorAutoComplete> = doctorAutoCompleteList.toMutableList()


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.doctor_autocomplete_item, parent, false)

        val textView = rowView.findViewById(R.id.doctorName) as TextView
        textView.text = (doctorAutoCompleteList[position].nothing)

        rowView.setOnClickListener {
            onDoctorSelectListener.onDoctorSelect(doctorAutoCompleteList[position])
        }

        return rowView
    }

    override fun getCount(): Int = doctorAutoCompleteList.size

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = FilterResults()

            val list = originalList
            val count = list.size
            val resultList = ArrayList<DoctorAutoComplete>(count)

            val filterString: String = constraint?.toString() ?: ""
            for (i in 0..(count - 1)) {
                val filterableString = list[i].nothing.toLowerCase()
                if (filterableString.toLowerCase().contains(filterString.toLowerCase())) {
                    resultList.add(list[i])
                }
            }

            results.values = resultList
            results.count = resultList.size

            return results
        }

        @SuppressWarnings("unchecked")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            doctorAutoCompleteList = (results?.values
                    ?: mutableListOf<DoctorAutoComplete>()) as MutableList<DoctorAutoComplete>
            notifyDataSetChanged()
        }
    }


}