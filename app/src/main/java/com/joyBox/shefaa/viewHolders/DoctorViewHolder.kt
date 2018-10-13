package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.views.SimpleRatingBar


class DoctorViewHolder : RecyclerView.ViewHolder {

    var context: Context

    constructor(view: View) : super(view) {
        ButterKnife.bind(this, view)
        context = view.context
    }


    @BindView(R.id.doctorname)
    lateinit var doctorName: TextView

    @BindView(R.id.doctorSpecializitions)
    lateinit var doctorSpecializitions: TextView

    @BindView(R.id.doctoraddress)
    lateinit var doctoraddress: TextView

    @BindView(R.id.doctorprice)
    lateinit var doctorprice: TextView

    @BindView(R.id.ratingBar)
    lateinit var simpleRatingBar: SimpleRatingBar

    fun bind(doctor: Doctor) {
        doctorName.setText(doctor.name)
        doctorSpecializitions.setText(doctor.getdoctorSpecializtion())
        doctoraddress.setText(doctor.fullAddress)
        val rt = doctor.rating
        simpleRatingBar.setRating(rt)
        if (!doctor.costs.equals("0"))
            doctorprice.setText(doctor.costs)
    }


}