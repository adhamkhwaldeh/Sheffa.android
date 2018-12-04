package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.CityEntity
import com.joyBox.shefaa.enums.CityEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.viewHolders.CityViewHolder

class CityRecyclerViewAdapter(val context: Context, val cityEnum: CityEnum, val cityList: MutableList<CityEntity>) :
        RecyclerView.Adapter<CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.city_item, parent, false)
        return CityViewHolder(view)
    }

    override fun getItemCount(): Int = cityList.size

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        val poJo = cityList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            when (cityEnum) {
                CityEnum.DOCTOR -> {
                    RxBus.publish(MessageEvent(EventActions.CityDoctor_Tag, poJo.city))
                }
                CityEnum.PHARMACIST -> {
                    RxBus.publish(MessageEvent(EventActions.CityPharmacy_Tag, poJo.city))
                }
                CityEnum.LABORATORY -> {
                    RxBus.publish(MessageEvent(EventActions.CityLab_Tag, poJo.city))
                }
            }
            //IntentHelper.startDoctorAddPrescriptionActivity(context, poJo)
        }
    }
}
