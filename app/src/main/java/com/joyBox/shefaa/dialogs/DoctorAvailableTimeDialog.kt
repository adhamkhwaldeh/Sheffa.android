package com.joyBox.shefaa.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.AppointmentAvailableTimeAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorAvailableTimeComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.entities.AvailableTime
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnAvailableTimeSelectListener
import com.joyBox.shefaa.networking.listeners.OnAppointmentAvailableTimeListener
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class DoctorAvailableTimeDialog : DialogFragment(), AppointmentContract.View, OnAvailableTimeSelectListener {

    lateinit var date: String

    lateinit var doctorAutoComplete: DoctorAutoComplete

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @Inject
    lateinit var presenter: AppointmentPresenter

    companion object {

        const val DoctorAvailableTimeDialog_Tag = "DoctorAvailableTimeDialog_Tag"

        fun newInstance(date: String, doctorAutoComplete: DoctorAutoComplete): DoctorAvailableTimeDialog {
            val doctorAvailableTimeDialog = DoctorAvailableTimeDialog()
            doctorAvailableTimeDialog.date = date
            doctorAvailableTimeDialog.doctorAutoComplete = doctorAutoComplete
            return doctorAvailableTimeDialog
        }
    }

    fun initDI() {
        val component = DaggerDoctorAvailableTimeComponent.builder()
                .appointmentModule(AppointmentModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadAvailableTime(doctorId = doctorAutoComplete.doctor_id, date = date)
    }

    fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(GridDividerDecoration(context))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.doctor_available_time_dialog_layout, container)
        ButterKnife.bind(this, mView)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()
    }

    /*Presenter started*/

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

    override fun onAvailableTimeLoadedSuccessfully(availableTime: AvailableTime) {
        recyclerView.adapter = AppointmentAvailableTimeAdapter(context, availableTime.availableTimes, this)
    }
    /*Presenter ended*/

    override fun onTimeSelect(time: String) {
        RxBus.publish(MessageEvent(EventActions.AvailableTimeSelected_Tag, time))
        dismiss()
    }

}