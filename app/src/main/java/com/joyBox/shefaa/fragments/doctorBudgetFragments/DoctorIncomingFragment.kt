package com.joyBox.shefaa.fragments.doctorBudgetFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.*
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerDoctorIncomingComponent
import com.joyBox.shefaa.di.module.ReportModule
import com.joyBox.shefaa.di.ui.ReportContract
import com.joyBox.shefaa.di.ui.ReportPresenter
import com.joyBox.shefaa.entities.AppointmentAutoComplete
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.viewModels.DoctorIncomingViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class DoctorIncomingFragment : BaseDoctorBudgetFragment(), ReportContract.View {

    companion object {
        fun getNewInstance(): BaseDoctorBudgetFragment {
            val f = DoctorIncomingFragment()
            f.titleRes = R.string.Incoming
            return f
        }

        fun getNewInstance(doctorAppointment: DoctorAppointment): BaseDoctorBudgetFragment {
            val f = DoctorIncomingFragment()
            f.titleRes = R.string.Incoming
            f.appointment = doctorAppointment
            return f
        }
    }

    lateinit var doctorIncomingViewHolder: DoctorIncomingViewHolder

    @Inject
    lateinit var presenter: ReportPresenter

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    var appointment: DoctorAppointment? = null

    fun initDI() {
        val component = DaggerDoctorIncomingComponent.builder()
                .reportModule(ReportModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_incoming_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        doctorIncomingViewHolder = DoctorIncomingViewHolder(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                onSaveButtonClick(stateLayout)
            }

            override fun onRequestPermission() {

            }
        })

        RxBus.listen(MessageEvent::class.java).subscribe {
            when (it.action) {
                EventActions.AppointmentAutoCompleteActivity_Tag -> {
                    val appointment = it.message as AppointmentAutoComplete
                    doctorIncomingViewHolder.setAppointment(appointment)
                }
            }
        }
    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        if (doctorIncomingViewHolder.isValid()) {
            presenter.addIncoming(doctorIncomingViewHolder.getAppointmentInvoiceModel())
        }
    }

    @Optional
    @OnFocusChange(R.id.appointmentName)
    fun onAppointmentFocusChange(view: View, isFocus: Boolean) {
        if (isFocus) {
            IntentHelper.startAppointmentAutoCompleteActivity(context!!)
        }
    }

    /*Appointment presenter started*/
    override fun showProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
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

    override fun onIncomingAddedSuccessfully() {
        Toast.makeText(context, R.string.receiptAddSuccessfully, Toast.LENGTH_LONG).show()
    }

    override fun onIncomingAddedFailed() {
        Toast.makeText(context, R.string.addReceiptFailed, Toast.LENGTH_LONG).show()
    }

    /*Appointment presenter ended*/


}