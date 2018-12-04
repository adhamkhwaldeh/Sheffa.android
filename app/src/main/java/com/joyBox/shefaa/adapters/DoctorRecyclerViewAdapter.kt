package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.DoctorViewHolder
import java.util.ArrayList

class DoctorRecyclerViewAdapter(val context: Context, var doctorList: MutableList<Doctor>) :
        RecyclerView.Adapter<DoctorViewHolder>(), Filterable {

    private val originalList: MutableList<Doctor> = doctorList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.doctor_item, parent, false)
        return DoctorViewHolder(view)
    }

    override fun getItemCount(): Int = doctorList.size

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        val poJo = doctorList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startDoctorDetailsActivity(context, poJo)
        }
    }

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = FilterResults()

            val list = originalList
            val count = list.size
            val resultList = ArrayList<Doctor>(count)

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
            doctorList = (results?.values
                    ?: mutableListOf<Doctor>()) as MutableList<Doctor>
            notifyDataSetChanged()
        }
    }

}