package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.DoctorAppointmentViewPagerAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorAppointmentComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.AppointmentFlagName
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import javax.inject.Inject

class DoctorAppointmentActivity : BaseActivity(), AppointmentContract.View {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager

    @Inject
    lateinit var presenter: AppointmentPresenter

    lateinit var progressDialog: ProgressDialog

    private fun initToolbar() {
        toolbar.setTitle(R.string.ManageAppointments)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initViewPager() {
        viewPager.adapter = DoctorAppointmentViewPagerAdapter(this, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun initDI() {
        val component = DaggerDoctorAppointmentComponent.builder()
                .appointmentModule(AppointmentModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

//        presenter.loadAvailableTime(doctorId = doctor.doctor_id, date = dateText.text.toString())

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_appointment_layout)
        ButterKnife.bind(this)
        initToolbar()
        initViewPager()

        progressDialog = ProgressDialog.newInstance()

        initDI()

        RxBus.listen(MessageEvent::class.java).subscribe {
            when (it.action) {
                EventActions.SwitchAppointmentStateStart_Tag -> {
                    val appointment = it.message as DoctorAppointment
                    val userId: String = UserRepository(this@DoctorAppointmentActivity).getClient()!!.user.uid
                    presenter.switchAppointmentState(NetworkingHelper.Doctor_Appointment_Url,
                            AppointmentFlagName.START, appointment.nid, userId, "flag")
                }
                EventActions.SwitchAppointmentStateEnd_Tag -> {
                    val appointment = it.message as DoctorAppointment
                    val userId: String = UserRepository(this@DoctorAppointmentActivity).getClient()!!.user.uid
                    presenter.switchAppointmentState(NetworkingHelper.Doctor_Appointment_Url,
                            AppointmentFlagName.END, appointment.nid, userId, "flag")
                }
            }
        }

    }

    /*Appointment presenter started*/

    override fun showProgress(show: Boolean) {
        if (show) {
            progressDialog.show(supportFragmentManager, ProgressDialog.ProgressDialog_Tag)
        } else {
            progressDialog.dismiss()
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible)
            Toast.makeText(baseContext, R.string.noconnection, Toast.LENGTH_LONG).show()
    }

    override fun onAppointmentSwitchedSuccessfully() {
        Toast.makeText(baseContext, R.string.AppointmentStateSwitchedSuccessfully, Toast.LENGTH_LONG).show()
    }

    override fun onAppointmentSwitchedFailed() {
        Toast.makeText(baseContext, R.string.SwitchAppointmentStateFailed, Toast.LENGTH_LONG).show()
    }
    /*Appointment presenter ended*/
}