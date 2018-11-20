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
import com.joyBox.shefaa.entities.AppointmentAutoComplete
import com.joyBox.shefaa.networking.listeners.OnAppiontmentSelectListener
import java.util.ArrayList

class AppointmentAutoCompleteListViewAdapter constructor(
        context: Context,
        private var appointmentAutoCompleteList : MutableList<AppointmentAutoComplete>
        , private val onAppiontmentSelectListener: OnAppiontmentSelectListener) :
        ArrayAdapter<AppointmentAutoComplete>(context, R.layout.appointment_autocomplete_item,
                appointmentAutoCompleteList), Filterable {

    private val originalList: MutableList<AppointmentAutoComplete> = appointmentAutoCompleteList.toMutableList()


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.appointment_autocomplete_item, parent, false)

        val textView = rowView.findViewById(R.id.doctorName) as TextView
        textView.text = (appointmentAutoCompleteList[position].title)

        rowView.setOnClickListener {
            onAppiontmentSelectListener.onAppointmentSelect(appointmentAutoCompleteList[position])
        }

        return rowView
    }

    override fun getCount(): Int = appointmentAutoCompleteList.size

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = FilterResults()

            val list = originalList
            val count = list.size
            val resultList = ArrayList<AppointmentAutoComplete>(count)

            val filterString: String = constraint?.toString() ?: ""
            for (i in 0..(count - 1)) {
                val filterableString = list[i].title.toLowerCase()
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
            appointmentAutoCompleteList = (results?.values
                    ?: mutableListOf<AppointmentAutoComplete>()) as MutableList<AppointmentAutoComplete>
            notifyDataSetChanged()
        }
    }
}

