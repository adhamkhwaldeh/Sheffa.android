package com.joyBox.shefaa.fragments.generalSearchFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Spinner
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.SpecializationSpinnerAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorGeneralSearchComponent
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.dialogs.CitiesDialog
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.entities.SpecialistAutoComplete
import com.joyBox.shefaa.enums.CityEnum
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.viewModels.DoctorGeneralSearchViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class DoctorGeneralSearchFragment : BaseGeneralSearchFragment(), DoctorContract.View {

    companion object {
        fun getNewInstance(): DoctorGeneralSearchFragment {
            val f = DoctorGeneralSearchFragment()
            f.titleRes = R.string.Doctor
            return f
        }
    }

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.specializationSpinner)
    lateinit var specializationSpinner: Spinner

    @Inject
    lateinit var presenter: DoctorPresenter

    lateinit var doctorGeneralSearchViewHolder: DoctorGeneralSearchViewHolder

    private fun initDI() {
        val component = DaggerDoctorGeneralSearchComponent.builder()
                .doctorModule(DoctorModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
//        presenter.loadDoctorList(NetworkingHelper.Doctor_Search_Url)
        presenter.loadSpecialistAutoComplete(NetworkingHelper.Specialist_AutoCompleteUrl)
//        val user = UserRepositoy(activity!!).getClient()
//        presenter.loadUserProfile(userId = user!!.user.uid, profileType = ProfileType.PATIENT.type)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_general_search_fragment_layout, container, false)
        doctorGeneralSearchViewHolder = DoctorGeneralSearchViewHolder(view)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadSpecialistAutoComplete(NetworkingHelper.Specialist_AutoCompleteUrl)
            }

            override fun onRequestPermission() {

            }
        })

        RxBus.listen(MessageEvent::class.java).subscribe {
            when (it.action) {
                EventActions.CityDoctor_Tag -> {
                    doctorGeneralSearchViewHolder.cityTextView.text = it.message as String
                }
            }
        }

    }

    @OnClick(R.id.searchBtn)
    fun onSearchButtonClick(view: View) {
        IntentHelper.startDoctorSearchActivity(activity!!, doctorGeneralSearchViewHolder.getDoctorSearchModel())
        Log.v("", "")
    }

    @OnClick(R.id.cityTextView)
    fun onCityTextViewClick(view: View) {
        val cityDialog = CitiesDialog.newInstance(CityEnum.DOCTOR)
        cityDialog.show(childFragmentManager, CitiesDialog.CitiesDialog_Tag)
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

    override fun onDoctorAutoCompleteSuccessfully(doctorAutoCompleteList: List<DoctorAutoComplete>) {

    }

    override fun onDoctorListLoadedSuccessfully(doctorList: MutableList<Doctor>) {
//        val specList: MutableList<String> = doctorList
//                .flatMap { it -> it.doctor_specialization }.toMutableList()
//        specializationSpinner.adapter = SpecializationSpinnerAdapter(context!!, specList)
        Log.v("", "")
    }

    override fun onSpecialistAutoCompleteLoadedSuccessfully(specialistAutoCompleteList: MutableList<SpecialistAutoComplete>) {
//        val specList: MutableList<String> = specialistAutoCompleteList
//                .map { it -> it.name }.toMutableList()

        val specList: MutableList<SpecialistAutoComplete> = mutableListOf()
        val spec = SpecialistAutoComplete()
        spec.name = resources.getString(R.string.All)
        specList.add(spec)
        specList.addAll(specialistAutoCompleteList)

        specializationSpinner.adapter = SpecializationSpinnerAdapter(context!!, specList)
        Log.v("", "")
    }

    /*Presenter ended*/
}