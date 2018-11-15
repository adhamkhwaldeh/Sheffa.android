package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.repositories.UserRepository


class AddReminderViewHolder(val view: View, val baseUrl: String) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.dateTextView)
    lateinit var dateTextView: TextView

    @BindView(R.id.timeTextView)
    lateinit var timeTextView: TextView

    val context: Context = view.context

    init {
        ButterKnife.bind(this, view)
    }

    fun isValid(): Boolean {
        if (dateTextView.text.isNullOrEmpty()) {
            return false
        } else if (timeTextView.text.isNullOrEmpty()) {
            return false
        }
        return true
    }

    fun getUrl(): String {
        val user = UserRepository(context).getClient()!!
        return baseUrl + "&first_time=2018-07-25 09:15:00&uid=" + user.user.uid
    }


}