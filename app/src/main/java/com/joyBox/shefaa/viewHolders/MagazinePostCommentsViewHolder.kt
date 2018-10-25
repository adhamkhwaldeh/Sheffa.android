package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.MagazinePostComment

/**
 * Created by Adhamkh on 2018-10-05.
 */
class MagazinePostCommentsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var context: Context

    @BindView(R.id.name)
    lateinit var name: TextView

    @BindView(R.id.value)
    lateinit var value: TextView

    init {
        context = view.context
        ButterKnife.bind(this, view)
    }

    fun bind(comment: MagazinePostComment) {
        name.text = (comment.name)
        value.text = comment.comment_body.und[0].value
    }

}