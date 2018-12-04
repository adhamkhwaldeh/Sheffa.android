package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.di.component.DaggerDoctorAppointmentInvoiceComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.entities.AppointmentAutoComplete
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.fragments.doctorBudgetFragments.DoctorIncomingFragment
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.viewModels.DoctorAppointmentInvoiceViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject


class DoctorAppointmentInvoiceActivity : BaseActivity(), AppointmentContract.View {

    companion object {
        const val DoctorAppointmentInvoiceActivity_Tag = "DoctorAppointmentInvoiceActivity_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @Inject
    lateinit var presenter: AppointmentPresenter

    lateinit var doctorAppointmentInvoiceViewHolder: DoctorAppointmentInvoiceViewHolder

    private fun initToolbar() {
        toolbar.setTitle(R.string.AddInvoice)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initDI() {
        val component = DaggerDoctorAppointmentInvoiceComponent.builder()
                .appointmentModule(AppointmentModule(this))
                .build()
        component.inject(this)

        presenter.attachView(this)
        presenter.subscribe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_appointment_invoice_layout)
        ButterKnife.bind(this)

        val jSon = intent.getStringExtra(DoctorAppointmentInvoiceActivity_Tag)
        val doctorAppointment = Gson().fromJson(jSon, DoctorAppointment::class.java)
        doctorAppointmentInvoiceViewHolder = DoctorAppointmentInvoiceViewHolder(findViewById(android.R.id.content))
        doctorAppointmentInvoiceViewHolder.setAppointment(doctorAppointment)

        initToolbar()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                onSaveButtonClick(stateLayout)
            }

            override fun onRequestPermission() {

            }
        })

//        supportFragmentManager.beginTransaction().replace(R.id.container,
//                DoctorIncomingFragment.getNewInstance(doctorAppointment)).commit()
    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        if (doctorAppointmentInvoiceViewHolder.isValid())
            presenter.addAppointmentInvoice(doctorAppointmentInvoiceViewHolder.getAppointmentInvoiceModel())
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

    override fun onAppointmentInvoiceAddedSuccessfully() {
        Toast.makeText(baseContext, R.string.InvoiceAddedSuccessfully, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onAddAppointmentInvoiceFailed() {
        Toast.makeText(baseContext, R.string.UnexpectedError, Toast.LENGTH_LONG).show()
    }
    /*Appointment presenter ended*/

}