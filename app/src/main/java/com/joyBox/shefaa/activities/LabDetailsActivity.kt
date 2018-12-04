package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.adapters.OpenHoursAdapter
import com.joyBox.shefaa.configurations.GlideApp
import com.joyBox.shefaa.dialogs.RatingDialog
import com.joyBox.shefaa.entities.Lab
import com.joyBox.shefaa.entities.models.MessageReplayModel
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.views.SimpleRatingBar

class LabDetailsActivity : BaseActivity() {
    companion object {
        const val LabDetailsActivity_Tag = "LabDetailsActivity_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.OpenHoursRecyclerView)
    lateinit var OpenHoursRecyclerView: RecyclerView

    @BindView(R.id.Address)
    lateinit var Address: TextView

    @BindView(R.id.ratingBar)
    lateinit var ratingBar: SimpleRatingBar

    @BindView(R.id.ratebtn)
    lateinit var ratebtn: Button

    @BindView(R.id.image_Person)
    lateinit var image_Person: ImageView

    @BindView(R.id.phoneNumber)
    lateinit var phoneNumber: TextView


    lateinit var lab: Lab

    fun initToolBar() {
        toolbar.setTitle(R.string.Doctor)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun bindData() {
//        SpecializationRecyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
//        SpecializationRecyclerView.setAdapter(SpecializationAdapter(applicationContext, pharmacy.spedoctor_specialization))

        OpenHoursRecyclerView.setLayoutManager(LinearLayoutManager(applicationContext))
        OpenHoursRecyclerView.setAdapter(OpenHoursAdapter(applicationContext, lab.lab_open_hours))

        toolbar.setTitle(lab.name)
        Address.setText(lab.address.name)
        var phone = lab.phone.joinToString { it }
        phone = phone.removeSuffix(",")
        phoneNumber.setText(phone)
//        Price.setText(pharmacy.costs)
        ratingBar.setRating(lab.rating)
        try {
            val url = lab.picture
            GlideApp.with(applicationContext).load(url)/*.resize(240, 170).centerCrop().fit()*/
                    .placeholder(R.drawable.userprofile).error(R.drawable.userprofile).into(image_Person
//            , object : Callback() {
//                fun onSuccess() {
//                    Log.v("Success", "Load Image")
//                }
//
//                fun onError() {
//                    Log.v("Fail", "Load Image")
//                }
//            }
            )
        } catch (ex: Exception) {

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lab_details_layout)
        ButterKnife.bind(this)
        initToolBar()
        val jSon = intent.getStringExtra(LabDetailsActivity_Tag)
        lab = Gson().fromJson(jSon, Lab::class.java)

        bindData()
    }

    @OnClick(R.id.ratebtn)
    fun onRatingButtonClick(view: View) {
        val dialog = RatingDialog.newInstance(lab.uid)
        dialog.show(supportFragmentManager, RatingDialog.RatingDialog_Tag)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.doctor_details_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.messages -> {
                var targetId = lab.uid
                targetId = targetId.removeSuffix(",")
                IntentHelper.startReplayMessageActivity(baseContext, MessageReplayModel(targetId, lab.name))
            }
        }
        return super.onOptionsItemSelected(item)
    }

}