package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.cores.AuthenticationCore
import com.joyBox.shefaa.entities.User
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.repositories.UserRepository

class MainDashBoardActivity : BaseActivity() {

    @BindView(R.id.patientBtn)
    lateinit var patientBtn: View

    @BindView(R.id.doctorBtn)
    lateinit var doctorBtn: View

    @BindView(R.id.pharmacyBtn)
    lateinit var pharmacyBtn: View

    @BindView(R.id.labBtn)
    lateinit var labBtn: View

    @BindView(R.id.manufacturesBtn)
    lateinit var manufacturesBtn: View

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    fun initToolbar() {
        toolbar.setTitle(R.string.Roles)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_dash_board_layout)
        ButterKnife.bind(this)
        initToolbar()
        val user: User = UserRepository(this).getClient()!!.user
//        if (!AuthenticationCore.isPatientOnly(user)) {
//            patientBtn.visibility = View.INVISIBLE
//        }

        if (!AuthenticationCore.isDoctor(user)) {
            doctorBtn.visibility = View.INVISIBLE
        } else {
            doctorBtn.visibility = View.VISIBLE
        }

        if(!AuthenticationCore.isPharmacy(user)){
            pharmacyBtn.visibility = View.INVISIBLE
        } else {
            pharmacyBtn.visibility = View.VISIBLE
        }

        if(!AuthenticationCore.isLaboratory(user)){
            labBtn.visibility = View.INVISIBLE
        } else {
            labBtn.visibility = View.VISIBLE
        }

        if(!AuthenticationCore.isSupport(user)){
            manufacturesBtn.visibility = View.INVISIBLE
        } else {
            manufacturesBtn.visibility = View.VISIBLE
        }

    }

    @OnClick(R.id.patientBtn)
    fun onPatientButtonClick(view: View) {
        IntentHelper.startPatientDashBoardActivity(this@MainDashBoardActivity)
    }

    @OnClick(R.id.doctorBtn)
    fun onDoctorButtonClick(view: View) {
        IntentHelper.startDoctorDashBoardActivity(this)
    }

}