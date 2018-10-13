package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.MagazinePost
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.MagazinePostViewHolder

class MagazinePostRecyclerViewAdapter(val context: Context,private val magazinePostList: List<MagazinePost>) :
        RecyclerView.Adapter<MagazinePostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagazinePostViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.magazine_post_item, parent, false)
        return MagazinePostViewHolder(view)
    }

    override fun getItemCount(): Int = magazinePostList.size

    override fun onBindViewHolder(holder: MagazinePostViewHolder, position: Int) {
        val poJo = magazinePostList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startMagazinePostsDetailsActivity(context, poJo)
        }
    }

}