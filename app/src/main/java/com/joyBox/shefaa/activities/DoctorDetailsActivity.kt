package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.joyBox.shefaa.adapters.OpenHoursAdapter
import com.joyBox.shefaa.adapters.SpecializationAdapter
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.views.SimpleRatingBar

class DoctorDetailsActivity : BaseActivity() {

    lateinit var doctor: Doctor

//    @BindView(R.id.fab)
//    lateinit var fab: FloatingActionButton

    @BindView(R.id.SpecializationRecyclerView)
    lateinit var SpecializationRecyclerView: RecyclerView

    @BindView(R.id.OpenHoursRecyclerView)
    lateinit var OpenHoursRecyclerView: RecyclerView

    @BindView(R.id.Address)
    lateinit var Address: TextView

    @BindView(R.id.Price)
    lateinit var Price: TextView

    @BindView(R.id.ratingBar)
    lateinit var ratingBar: SimpleRatingBar

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.ratebtn)
    lateinit var ratebtn: Button

    @BindView(R.id.image_Person)
    lateinit var image_Person: ImageView

    companion object {
        const val DoctorDetailsActivity_Tag = "DoctorDetailsActivity_Tag"
    }

    fun initToolBar() {
        toolbar.setTitle(R.string.Doctor)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_details_layout)
        ButterKnife.bind(this)
        initToolBar()
        val json = intent.getStringExtra(DoctorDetailsActivity_Tag)
        doctor = Gson().fromJson(json, Doctor::class.java)

        bindData()

    }

    @OnClick(R.id.appointmentFab)
    fun onAppointmentFabClick(view:View){
        IntentHelper.startAppointmentAddActivity(this,doctor)
    }

    private fun bindData() {
        SpecializationRecyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
        SpecializationRecyclerView.setAdapter(SpecializationAdapter(applicationContext, doctor.doctor_specialization))

        OpenHoursRecyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
        OpenHoursRecyclerView.setAdapter(OpenHoursAdapter(applicationContext, doctor.clinic_open_hourses))

        toolbar.setTitle(doctor.getName())
        Address.setText(doctor.fullAddress)
        Price.setText(doctor.costs)
        ratingBar.setRating(doctor.rating)
        try {
            val url = doctor.pictureUrl
//            Glide.with(applicationContext).load(url)/*.resize(240, 170).centerCrop()*/.fit()
//                    .placeholder(R.drawable.userprofile).error(R.drawable.userprofile).into(image_Person, object : Callback() {
//                fun onSuccess() {
//                    Log.v("Success", "Load Image")
//                }
//
//                fun onError() {
//                    Log.v("Fail", "Load Image")
//                }
//            })
        } catch (ex: Exception) {
        }

    }

}