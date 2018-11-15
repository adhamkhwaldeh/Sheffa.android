package com.joyBox.shefaa.activities.doctor

import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.DoctorSpecializationTagRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorProfileComponent
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.module.MedicalProfileModule
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.di.ui.MedicalProfileContract
import com.joyBox.shefaa.di.ui.MedicalProfilePresenter
import com.joyBox.shefaa.dialogs.DoctorSpecializationDialog
import com.joyBox.shefaa.entities.DoctorProfile
import com.joyBox.shefaa.entities.SpecialistAutoComplete
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.enums.ProfileType
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.viewModels.DoctorProfileViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DoctorProfileActivity : BaseActivity(), MedicalProfileContract.View, DoctorContract.View {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @Inject
    lateinit var presenter: MedicalProfilePresenter

    @Inject
    lateinit var doctorPresenter: DoctorPresenter

    lateinit var doctorProfileViewHolder: DoctorProfileViewHolder

    var isSpecializationListLoad: Boolean = true

    private fun initToolBar() {
        toolbar.setTitle(R.string.DoctorProfile)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initDI() {
        val component = DaggerDoctorProfileComponent.builder()
                .doctorModule(DoctorModule(this))
                .medicalProfileModule(MedicalProfileModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

        doctorPresenter.attachView(this)
        doctorPresenter.subscribe()

        doctorPresenter.loadSpecialistAutoComplete(NetworkingHelper.Specialist_AutoCompleteUrl)

    }

    private fun loadProfile() {
        val user = UserRepository(this).getClient()!!.user
        presenter.loadDoctorProfile(userId = user.uid, profileType = ProfileType.DOCTOR.type)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_profile_layout)
        ButterKnife.bind(this)
        doctorProfileViewHolder = DoctorProfileViewHolder(findViewById(android.R.id.content))

        initToolBar()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                if (isSpecializationListLoad) {
                    doctorPresenter.loadSpecialistAutoComplete(NetworkingHelper.Specialist_AutoCompleteUrl)
                } else {
                    loadProfile()
                }
            }

            override fun onRequestPermission() {

            }
        })

        RxBus.listen(MessageEvent::class.java).subscribe {
            when (it.action) {
                EventActions.DoctorSpecializationDialog_Tag -> {
                    doctorProfileViewHolder.specializationRecyclerView.adapter =
                            DoctorSpecializationTagRecyclerViewAdapter(baseContext, it.message as MutableList<SpecialistAutoComplete>)
                }
            }
        }

    }

    private fun initTimePicker(view: TextView) {
        var calendar = Calendar.getInstance()
        val timePicker = TimePickerDialog(this@DoctorProfileActivity, R.style.AppTheme_DialogSlideAnimwithback,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.HOUR, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)
                    calendar.set(Calendar.AM_PM, Calendar.AM)
                    val date = calendar.time
                    val sdf = SimpleDateFormat("HH:mm", Locale.ENGLISH)
                    val sdt = sdf.format(date)
                    view.text = sdt
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true)
        timePicker.show()
    }

    @OnClick(R.id.sundayStartDate, R.id.sundayToDate, R.id.monDayStartDate, R.id.monDayToDate,
            R.id.tuesdayStartDate, R.id.tuesdayToDate, R.id.wednesdayStartDate, R.id.wednesdayToDate,
            R.id.thursdayStartDate, R.id.thursdayToDate, R.id.fridayStartDate, R.id.fridayToDate,
            R.id.saturdayStartDate, R.id.saturdayToDate)
    fun onTimeClick(view: View) {
        initTimePicker(view as TextView)
    }

    @OnClick(R.id.addSpecializationBtn)
    fun onAddSpecializationButtonClick(view: View) {

        val list: MutableList<SpecialistAutoComplete> = mutableListOf()
        val selectedList = (doctorProfileViewHolder.specializationRecyclerView.adapter
                as DoctorSpecializationTagRecyclerViewAdapter).specializationList
        doctorProfileViewHolder.specialistAutoCompleteList?.forEach {
            for (specialistAutoComplete in selectedList) {
                if (specialistAutoComplete.tid == it.tid)
                    it.selected = true
            }
            list.add(it)
        }

        val doctorSpecializationDialog = DoctorSpecializationDialog.newInstance(mutableListOf(), list)
        doctorSpecializationDialog.show(supportFragmentManager, DoctorSpecializationDialog.DoctorSpecializationDialog_Tag)
    }


    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        presenter.updateDoctorProfile(doctorProfileViewHolder.getUpdateUrl())
    }

    /*Medical profile presenter started*/
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

    override fun onDoctorProfileLoaded(doctorProfile: DoctorProfile) {
        doctorProfileViewHolder.bind(doctorProfile)
    }

    override fun onDoctorProfileUpdateSuccessfully() {
        Toast.makeText(baseContext, resources.getText(R.string.profileUpdateSuccessfully), Toast.LENGTH_LONG).show()
        Log.v("", "")
    }

    /*Medical profile presenter ended*/


    /*Doctor presenter started*/
    override fun onSpecialistAutoCompleteLoadedSuccessfully(specialistAutoCompleteList: MutableList<SpecialistAutoComplete>) {
        doctorProfileViewHolder.bindSpecialistList(specialistAutoCompleteList)
        isSpecializationListLoad = false
        loadProfile()

    }
    /*Doctor presenter ended*/

}
