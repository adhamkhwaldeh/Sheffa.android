package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.GuardianshipEntity
import com.joyBox.shefaa.entities.MagazinePost
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.GuardianshipViewHolder
import com.joyBox.shefaa.viewHolders.MagazinePostViewHolder

/**
 * Created by Adhamkh on 2018-10-05.
 */
class GuardianshipRecyclerViewAdapter (val context: Context, private val guardianshipEntityList : List<GuardianshipEntity>) :
        RecyclerView.Adapter<GuardianshipViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuardianshipViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.guardianship_item, parent, false)
        return GuardianshipViewHolder(view)
    }

    override fun getItemCount(): Int = guardianshipEntityList.size

    override fun onBindViewHolder(holder: GuardianshipViewHolder, position: Int) {
        val poJo = guardianshipEntityList[position]
        holder.bind(poJo)
//        holder.itemView.setOnClickListener {
//            IntentHelper.startMagazinePostsDetailsActivity(context, poJo)
//        }
    }

}