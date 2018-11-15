package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.di.component.DaggerAppointmentDetailsComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.dialogs.AppointmentCancelAlertDialog
import com.joyBox.shefaa.entities.AppointmentEntity
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnAppointmentCancelListener
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.viewModels.AppointmentDetailsViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject


class AppointmentDetailsActivity : BaseActivity(), AppointmentContract.View {

    companion object {
        const val AppointmentDetailsActivity_Appointment_Tag = "AppointmentDetailsActivity_Appointment_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @Inject
    lateinit var presenter: AppointmentPresenter

    private lateinit var appointmentDetailsViewHolder: AppointmentDetailsViewHolder

    private fun initToolBar() {
        toolbar.setTitle(R.string.AppointmentDetail)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initDI() {
        val component = DaggerAppointmentDetailsComponent.builder()
                .appointmentModule(AppointmentModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appointment_details_layout)
        ButterKnife.bind(this)
        initToolBar()
        initDI()
        appointmentDetailsViewHolder = AppointmentDetailsViewHolder(findViewById(android.R.id.content))

        val jSon = intent.getStringExtra(AppointmentDetailsActivity_Appointment_Tag)
        appointmentDetailsViewHolder.bind(Gson().fromJson(jSon, AppointmentEntity::class.java))

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.deleteAppointment(appointmentId = appointmentDetailsViewHolder.appointment.nid)
            }

            override fun onRequestPermission() {

            }
        })

    }

    @OnClick(R.id.cancelBtn)
    fun onCancelButtonClick(view: View) {
        val dialog = AppointmentCancelAlertDialog()
        dialog.onAppointmentCancelListener = object : OnAppointmentCancelListener {
            override fun onSubmit() {
                presenter.deleteAppointment(appointmentId = appointmentDetailsViewHolder.appointment.nid)
            }

            override fun onCancelAction() {
                dialog.dismiss()
            }
        }
        dialog.show(supportFragmentManager, AppointmentCancelAlertDialog.AppointmentCancelAlertDialog_Tag)
    }

    /*Appointment presenter started*/
    override fun showProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showEmptyView(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun onAppointmentDeletedSuccessfully() {
        Toast.makeText(baseContext, baseContext.resources.getString(R.string.AppointmentDeletedSuccessfully), Toast.LENGTH_LONG).show()
    }

    override fun onAppointmentDeletedFailed() {
        Toast.makeText(baseContext, baseContext.resources.getString(R.string.CancelAppointmentFailed), Toast.LENGTH_LONG).show()
    }
    /*Appointment presenter ended*/


}