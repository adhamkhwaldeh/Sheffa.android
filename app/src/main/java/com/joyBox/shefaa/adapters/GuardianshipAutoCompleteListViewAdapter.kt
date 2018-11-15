package com.joyBox.shefaa.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.GuardianshipAutoComplete
import com.joyBox.shefaa.listeners.OnGuardianshipSelectListener
import java.util.ArrayList

class GuardianshipAutoCompleteListViewAdapter constructor(
        context: Context,
        private var guardianshipAutoCompleteList: MutableList<GuardianshipAutoComplete>
        , private val onGuardianshipSelectListener: OnGuardianshipSelectListener) :
        ArrayAdapter<GuardianshipAutoComplete>(context, R.layout.doctor_autocomplete_item,
                guardianshipAutoCompleteList), Filterable {

    private val originalList: MutableList<GuardianshipAutoComplete> = guardianshipAutoCompleteList.toMutableList()


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.doctor_autocomplete_item, parent, false)

        val textView = rowView.findViewById(R.id.doctorName) as TextView
        textView.text = (guardianshipAutoCompleteList[position].first_Name + " " +
                guardianshipAutoCompleteList[position].last_Name)

        rowView.setOnClickListener {
            onGuardianshipSelectListener.onGuardianshipSelect(guardianshipAutoCompleteList[position])
        }

        return rowView
    }

    override fun getCount(): Int = guardianshipAutoCompleteList.size

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = FilterResults()

            val list = originalList
            val count = list.size
            val resultList = ArrayList<GuardianshipAutoComplete>(count)

            val filterString: String = constraint?.toString() ?: ""
            for (i in 0..(count - 1)) {
                val filterableString1 = list[i].first_Name.toLowerCase()
                val filterableString2 = list[i].last_Name.toLowerCase()
                if (filterableString1.toLowerCase().contains(filterString.toLowerCase())) {
                    resultList.add(list[i])
                } else if (filterableString2.toLowerCase().contains(filterString.toLowerCase())) {
                    resultList.add(list[i])
                }
            }

            results.values = resultList
            results.count = resultList.size

            return results
        }

        @SuppressWarnings("unchecked")
        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            guardianshipAutoCompleteList = (results?.values
                    ?: mutableListOf<GuardianshipAutoComplete>()) as MutableList<GuardianshipAutoComplete>
            notifyDataSetChanged()
        }
    }


}