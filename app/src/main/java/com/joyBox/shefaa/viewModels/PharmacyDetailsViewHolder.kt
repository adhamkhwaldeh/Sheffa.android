package com.joyBox.shefaa.viewModels

import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.ButterKnife
import com.joyBox.shefaa.entities.Pharmacy

/**
 * Created by Adhamkh on 2018-10-27.
 */
class PharmacyDetailsViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(pharmacy: Pharmacy) {

    }
}