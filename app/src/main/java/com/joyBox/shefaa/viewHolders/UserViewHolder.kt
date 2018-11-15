package com.joyBox.shefaa.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import android.widget.ToggleButton
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.AutoCompleteUser

/**
 * Created by Adhamkh on 2018-11-10.
 */
class UserViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.user_toggle)
    lateinit var user_toggle: ToggleButton

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(user: AutoCompleteUser) {
        user_toggle.textOff = user.fullName
        user_toggle.textOn = user.fullName
        user_toggle.text = user.fullName
        user_toggle.setOnCheckedChangeListener(null)
        user_toggle.isChecked = user.selected
    }

}