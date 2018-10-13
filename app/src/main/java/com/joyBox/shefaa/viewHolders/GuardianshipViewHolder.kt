package com.joyBox.shefaa.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.GuardianshipEntity

/**
 * Created by Adhamkh on 2018-10-05.
 */
class GuardianshipViewHolder : RecyclerView.ViewHolder {

    @BindView(R.id.guardianshipName)
    lateinit var guardianshipName: TextView

    constructor(view: View) : super(view) {
        ButterKnife.bind(this, view)
    }

    fun bind(guardianshipEntity: GuardianshipEntity) {
        guardianshipName.text = guardianshipEntity.guardian_name
    }

}