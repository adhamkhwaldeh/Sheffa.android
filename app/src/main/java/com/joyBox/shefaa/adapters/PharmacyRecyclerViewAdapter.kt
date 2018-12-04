package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Pharmacy
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.PharmacyViewHolder
import java.util.ArrayList

class PharmacyRecyclerViewAdapter(val context: Context, var pharmacyList: MutableList<Pharmacy>) :
        RecyclerView.Adapter<PharmacyViewHolder>(), Filterable {

    private val originalList: MutableList<Pharmacy> = pharmacyList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PharmacyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.pharmacy_item, parent, false)
        return PharmacyViewHolder(view)
    }

    override fun getItemCount(): Int = pharmacyList.size

    override fun onBindViewHolder(holder: PharmacyViewHolder, position: Int) {
        val poJo = pharmacyList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startPharmacyDetailsActivity(context, poJo)
        }
    }

    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = FilterResults()

            val list = originalList
            val count = list.size
            val resultList = ArrayList<Pharmacy>(count)

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
            pharmacyList = (results?.values
                    ?: mutableListOf<Pharmacy>()) as MutableList<Pharmacy>
            notifyDataSetChanged()
        }
    }

}