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
import com.joyBox.shefaa.entities.ActiveMaterialAutoComplete
import com.joyBox.shefaa.entities.MedicineAutoComplete
import com.joyBox.shefaa.listeners.OnActiveMaterialSelectListener
import com.joyBox.shefaa.listeners.OnMedicineSelectListener
import java.util.ArrayList

class ActiveMaterialAutoCompleteListViewAdapter constructor(
        context: Context,
        private var medicineAutoCompleteList: MutableList<ActiveMaterialAutoComplete>
        , private val onActiveMaterialSelectListener: OnActiveMaterialSelectListener) :
        ArrayAdapter<ActiveMaterialAutoComplete>(context, R.layout.doctor_autocomplete_item,
                medicineAutoCompleteList), Filterable {

    private val originalList: MutableList<ActiveMaterialAutoComplete> = medicineAutoCompleteList.toMutableList()


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView = inflater.inflate(R.layout.doctor_autocomplete_item, parent, false)

        val textView = rowView.findViewById(R.id.doctorName) as TextView
        textView.text = (medicineAutoCompleteList[position].name)

        rowView.setOnClickListener {
            onActiveMaterialSelectListener.onActiveMaterialSelect(medicineAutoCompleteList[position])
        }

        return rowView
    }

    override fun getCount(): Int = medicineAutoCompleteList.size

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = FilterResults()

            val list = originalList
            val count = list.size
            val resultList = ArrayList<ActiveMaterialAutoComplete>(count)

            val filterString: String = constraint?.toString() ?: ""
            for (i in 0..(count - 1)) {
                val filterableString = list[i].name.toLowerCase()
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
            medicineAutoCompleteList = (results?.values
                    ?: mutableListOf<ActiveMaterialAutoComplete>()) as MutableList<ActiveMaterialAutoComplete>
            notifyDataSetChanged()
        }
    }


}