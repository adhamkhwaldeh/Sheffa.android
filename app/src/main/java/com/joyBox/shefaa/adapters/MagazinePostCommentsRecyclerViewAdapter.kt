package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.MagazinePostComment
import com.joyBox.shefaa.viewHolders.MagazinePostCommentsViewHolder

/**
 * Created by Adhamkh on 2018-10-05.
 */
class MagazinePostCommentsRecyclerViewAdapter(val context: Context,
                                              private val magazinePostCommentList: List<MagazinePostComment>)
    : RecyclerView.Adapter<MagazinePostCommentsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MagazinePostCommentsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.magazine_post_comment_item, parent, false)
        return MagazinePostCommentsViewHolder(view)
    }

    override fun getItemCount(): Int = magazinePostCommentList.size

    override fun onBindViewHolder(holder: MagazinePostCommentsViewHolder, position: Int) {
        val poJo = magazinePostCommentList[position]
        holder.bind(poJo)
    }
}