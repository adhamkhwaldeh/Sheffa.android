package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.TestResultEntity
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.DoctorTestsResultViewHolder

/**
 * Created by Adhamkh on 2018-11-16.
 */
class DoctorTestsResultRecyclerViewAdapter  constructor(val context: Context, val testResultList: MutableList<TestResultEntity>)
    : RecyclerView.Adapter<DoctorTestsResultViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorTestsResultViewHolder {
        return DoctorTestsResultViewHolder(LayoutInflater.from(context).inflate(R.layout.doctor_tests_result_item, parent, false));
    }

    override fun getItemCount(): Int = testResultList.size


    override fun onBindViewHolder(holder: DoctorTestsResultViewHolder, position: Int) {
        val poJo = testResultList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startTestsResultDetailsActivity(context, poJo)
        }
    }
}