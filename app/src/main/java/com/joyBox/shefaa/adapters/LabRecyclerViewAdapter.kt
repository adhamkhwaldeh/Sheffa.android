package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Lab
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.LabViewHolder
import java.util.ArrayList

class LabRecyclerViewAdapter(val context: Context, var labList: MutableList<Lab>) :
        RecyclerView.Adapter<LabViewHolder>(), Filterable {

    private val originalList: MutableList<Lab> = labList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LabViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.lab_item, parent, false)
        return LabViewHolder(view)
    }

    override fun getItemCount(): Int = labList.size

    override fun onBindViewHolder(holder: LabViewHolder, position: Int) {
        val poJo = labList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startLabDetailsActivity(context, poJo)
        }
    }


    override fun getFilter() = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = FilterResults()

            val list = originalList
            val count = list.size
            val resultList = ArrayList<Lab>(count)

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
            labList = (results?.values
                    ?: mutableListOf<Lab>()) as MutableList<Lab>
            notifyDataSetChanged()
        }
    }
}