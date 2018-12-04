package com.joyBox.shefaa.activities.autoComplete

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.DoctorAutoCompleteListViewAdapter
import com.joyBox.shefaa.adapters.MedicineAutoCompleteListViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorAutoCompleteComponent
import com.joyBox.shefaa.di.component.DaggerMedicineAutoCompleteComponent
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.module.MedicineModule
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.di.ui.MedicineContract
import com.joyBox.shefaa.di.ui.MedicinePresenter
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.entities.MedicineAutoComplete
import com.joyBox.shefaa.entities.mappers.DoctorDoctorAutoCompleteMapper
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnDoctorSelectListener
import com.joyBox.shefaa.listeners.OnMedicineSelectListener
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject


class MedicineAutoCompleteActivity : BaseActivity(), MedicineContract.View, OnMedicineSelectListener {

    @BindView(R.id.searchView)
    lateinit var searchView: MaterialSearchView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var medicinePresenter: MedicinePresenter


    fun initToolBar() {
        toolbar.setTitle(R.string.DoctorAutoComplete)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initSearch() {
        searchView.setSubmitOnClick(true)
        searchView.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                KeywordValue.text = query
//                if (KeywordValue.text.isNotEmpty())
//                    KeywordValue.visibility = View.VISIBLE
//                else
//                    KeywordValue.visibility = View.GONE
//                clearData()
                searchView.closeSearch()

//                doctorAutoCompleteEditText.setText((if (query != null) query else ""))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                if (searchView.setAdapter() != null)
//                    doctorPresenter.loadDoctorAutoComplete(NetworkingHelper.Doctors_Short_List_ServiceUrl)
                return false
            }
        })


    }

    fun initDI() {
        val component = DaggerMedicineAutoCompleteComponent.builder()
                .medicineModule(MedicineModule(this))
                .build()
        component.inject(this)
        medicinePresenter.attachView(this)
        medicinePresenter.subscribe()
        medicinePresenter.loadMedicineAutoCompleteList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_auto_complete)
        ButterKnife.bind(this)
        initToolBar()
        initDI()
        initSearch()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                medicinePresenter.loadMedicineAutoCompleteList()
            }

            override fun onRequestPermission() {

            }
        })

//        searchView.showSearch(true)
//        searchView.requestFocus()
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }


    /*Doctor Presenter started*/
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

    override fun onMedicineAutoCompleteListLoadedSuccessfully(medicineList: MutableList<MedicineAutoComplete>) {
        searchView.showSearch()
        searchView.setAdapter(MedicineAutoCompleteListViewAdapter(context = baseContext,
                medicineAutoCompleteList = medicineList,
                onMedicineSelectListener = this@MedicineAutoCompleteActivity))

        Log.v("", "")
    }

    /*Doctor Presenter ended*/

    override fun onMedicineSelect(medicineAutoComplete: MedicineAutoComplete) {
        RxBus.publish(MessageEvent(EventActions.MedicineAutoCompleteActivity_Tag, medicineAutoComplete))
        finish()
        Log.v("", "")
    }

}