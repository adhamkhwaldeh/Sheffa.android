package com.joyBox.shefaa.fragments.doctorAppointmentFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerDoctorShiftAppointmentComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import javax.inject.Inject

class DoctorShiftAppointmentFragment : BaseDoctorAppointmentFragment(), AppointmentContract.View {

    companion object {
        fun getNewInstance(): BaseDoctorAppointmentFragment {
            val f = DoctorShiftAppointmentFragment()
            f.titleRes = R.string.ShiftAppoitment
            return f
        }
    }

    @Inject
    lateinit var presenter: AppointmentPresenter

    fun initDI() {
        val component = DaggerDoctorShiftAppointmentComponent.builder()
                .appointmentModule(AppointmentModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_shift_appointment_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDI()

    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {

    }
    /*Presenter ended*/
}
