package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Pharmacy
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.PharmacyViewHolder

/**
 * Created by Adhamkh on 2018-10-12.
 */
class PharmacyRecyclerViewAdapter (val context: Context, val pharmacyList: MutableList<Pharmacy>) :
        RecyclerView.Adapter<PharmacyViewHolder>() {

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

}