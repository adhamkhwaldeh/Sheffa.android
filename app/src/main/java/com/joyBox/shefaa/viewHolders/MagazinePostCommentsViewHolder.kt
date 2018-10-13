package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import com.joyBox.shefaa.entities.MagazinePostComment

/**
 * Created by Adhamkh on 2018-10-05.
 */
class MagazinePostCommentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var context: Context

    init {
        context = view.context
    }

    fun bind(comment: MagazinePostComment) {

    }
}