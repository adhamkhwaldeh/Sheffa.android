package com.joyBox.shefaa.fragments.doctorAppointmentFragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.DoctorAppointmentRecyclerViewAdapter
import com.joyBox.shefaa.adapters.DoctorAppointmentTreatmentRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorCalendarClinicComponent
import com.joyBox.shefaa.di.module.AppointmentModule
import com.joyBox.shefaa.di.ui.AppointmentContract
import com.joyBox.shefaa.di.ui.AppointmentPresenter
import com.joyBox.shefaa.entities.DoctorAppointment
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import it.beppi.tristatetogglebutton_library.TriStateToggleButton
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DoctorCalendarClinicFragment : BaseDoctorAppointmentFragment(), AppointmentContract.View {

    companion object {
        fun getNewInstance(): BaseDoctorAppointmentFragment {
            val f = DoctorCalendarClinicFragment()
            f.titleRes = R.string.Calendar
            return f
        }
    }

    @Inject
    lateinit var presenter: AppointmentPresenter

    @BindView(R.id.dateText)
    lateinit var dateText: TextView

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.appointmentPlace)
    lateinit var appointmentPlace: TriStateToggleButton

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview


    fun initDI() {
        val component = DaggerDoctorCalendarClinicComponent.builder()
                .appointmentModule(AppointmentModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadDoctorAppointments(generateRequestUrl())
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(GridDividerDecoration(context))
    }

    private fun initTimePicker() {
        var calendar = Calendar.getInstance()
        val timePicker = DatePickerDialog(this@DoctorCalendarClinicFragment.context, R.style.AppTheme_DialogSlideAnimwithback,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = calendar.time
                    val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                    val sdt = sdf.format(date)
                    dateText.text = sdt
                    presenter.loadDoctorAppointments(generateRequestUrl())
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        timePicker.show()
    }

    private fun generateRequestUrl(): String {
        val client = UserRepository(context!!).getClient()!!
        return NetworkingHelper.DoctorAppointmentUrl + "?doctor_id=" + client.user.uid
//        urgent(0-1), home(0-1), date(Format: 14/08/2018)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_calendar_clinic_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initDI()

        appointmentPlace.setOnToggleChanged(object : TriStateToggleButton.OnToggleChanged {
            override fun onToggle(toggleStatus: TriStateToggleButton.ToggleStatus?,
                                  booleanToggleStatus: Boolean, toggleIntValue: Int) {
                when (toggleStatus) {
                    TriStateToggleButton.ToggleStatus.off -> {
                        presenter.loadDoctorAppointments(generateRequestUrl())
                    }
                    TriStateToggleButton.ToggleStatus.on -> {
                        presenter.loadDoctorAppointments(generateRequestUrl())
                    }
                    TriStateToggleButton.ToggleStatus.mid -> {
                        presenter.loadDoctorAppointments(generateRequestUrl())
                    }
                }
            }
        })

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadDoctorAppointments(generateRequestUrl())
            }

            override fun onRequestPermission() {

            }
        })
    }

    @OnClick(R.id.dateLayout)
    fun onDateLayoutClick(view: View) {
        initTimePicker()
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

    override fun onDoctorAppointmentsLoaded(doctorAppointmentList: MutableList<DoctorAppointment>) {
        recyclerView.adapter = DoctorAppointmentTreatmentRecyclerViewAdapter(context = context!!,
                doctorAppointmentList = doctorAppointmentList)
    }
    /*Presenter ended*/

}