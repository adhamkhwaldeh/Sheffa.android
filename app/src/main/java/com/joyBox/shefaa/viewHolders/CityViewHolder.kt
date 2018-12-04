package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.CityEntity

/**
 * Created by Adhamkh on 2018-11-28.
 */
class CityViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.cityName)
    lateinit var cityName: TextView

    val context: Context = view.context

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(cityEntity: CityEntity) {
        cityName.text = cityEntity.city
    }

}