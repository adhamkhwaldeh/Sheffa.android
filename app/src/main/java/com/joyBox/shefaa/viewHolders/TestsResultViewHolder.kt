package com.joyBox.shefaa.viewHolders

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.TestResultEntity

/**
 * Created by Adhamkh on 2018-08-21.
 */
class TestsResultViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.doctorName)
    lateinit var doctorName: TextView

    @BindView(R.id.testName)
    lateinit var testName: TextView

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(testResultEntity: TestResultEntity) {
        doctorName.text = testResultEntity.doctor_name
        testName.text = testResultEntity.test_name
    }

}