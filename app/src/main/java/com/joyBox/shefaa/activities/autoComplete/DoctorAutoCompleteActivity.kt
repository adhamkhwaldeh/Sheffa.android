package com.joyBox.shefaa.activities.autoComplete

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.DoctorAutoCompleteListViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorAutoCompleteComponent
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.entities.mappers.DoctorDoctorAutoCompleteMapper
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnDoctorSelectListener
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject


class DoctorAutoCompleteActivity : BaseActivity(), DoctorContract.View, OnDoctorSelectListener {

    @BindView(R.id.searchView)
    lateinit var searchView: MaterialSearchView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var doctorPresenter: DoctorPresenter


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
        val component = DaggerDoctorAutoCompleteComponent.builder()
                .doctorModule(DoctorModule(this))
                .build()
        component.inject(this)
        doctorPresenter.attachView(this)
        doctorPresenter.subscribe()
        doctorPresenter.loadDoctorList(NetworkingHelper.Doctor_Search_Url)

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
                doctorPresenter.loadDoctorList(NetworkingHelper.Doctor_Search_Url)
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

    override fun onDoctorListLoadedSuccessfully(doctorList: MutableList<Doctor>) {
        searchView.showSearch()
        searchView.setAdapter(DoctorAutoCompleteListViewAdapter(context = baseContext,
                doctorAutoCompleteList = doctorList.map {
                    DoctorDoctorAutoCompleteMapper.doctorDoctorAutoCompleteTransform(it)
                }.toMutableList(),
                onDoctorSelectListener = this@DoctorAutoCompleteActivity))

        Log.v("", "")
    }

    override fun onDoctorAutoCompleteSuccessfully(doctorAutoCompleteList: List<DoctorAutoComplete>) {
        searchView.showSearch()
        searchView.setAdapter(DoctorAutoCompleteListViewAdapter(context = baseContext,
                doctorAutoCompleteList = doctorAutoCompleteList.toMutableList(),
                onDoctorSelectListener = this@DoctorAutoCompleteActivity))

        Log.v("", "")
    }
    /*Doctor Presenter ended*/


    override fun onDoctorSelect(doctorAutoComplete: DoctorAutoComplete) {
        RxBus.publish(MessageEvent(EventActions.DoctorAutoCompleteActivity_Tag, doctorAutoComplete))
        finish()
        Log.v("", "")
    }

}