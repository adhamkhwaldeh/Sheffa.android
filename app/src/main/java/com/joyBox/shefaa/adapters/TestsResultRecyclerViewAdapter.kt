package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.TestResultEntity
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewHolders.TestsResultViewHolder

class TestsResultRecyclerViewAdapter constructor(val context: Context, val testResultList: MutableList<TestResultEntity>)
    : RecyclerView.Adapter<TestsResultViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestsResultViewHolder {
        return TestsResultViewHolder(LayoutInflater.from(context).inflate(R.layout.tests_result_item, parent, false));
    }

    override fun getItemCount(): Int = testResultList.size


    override fun onBindViewHolder(holder: TestsResultViewHolder, position: Int) {
        val poJo = testResultList[position]
        holder.bind(poJo)
        holder.itemView.setOnClickListener {
            IntentHelper.startTestsResultDetailsActivity(context, poJo)
        }
    }
}