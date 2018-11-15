package com.joyBox.shefaa.activities.autoComplete

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.GuardianshipAutoCompleteListViewAdapter
import com.joyBox.shefaa.di.component.DaggerGuardianshipAutoCompleteComponent
import com.joyBox.shefaa.di.module.GuardianshipModule
import com.joyBox.shefaa.di.ui.GuardianshipContract
import com.joyBox.shefaa.di.ui.GuardianshipPresenter
import com.joyBox.shefaa.entities.GuardianshipAutoComplete
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnGuardianshipSelectListener
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject


class GuardianshipAutoCompleteActivity : BaseActivity(), GuardianshipContract.View, OnGuardianshipSelectListener {

    @BindView(R.id.searchView)
    lateinit var searchView: MaterialSearchView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var guardianshipPresenter: GuardianshipPresenter

    fun initToolBar() {
        toolbar.setTitle(R.string.GuardianshipName)
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
        val component = DaggerGuardianshipAutoCompleteComponent.builder()
                .guardianshipModule(GuardianshipModule(this))
                .build()
        component.inject(this)
        guardianshipPresenter.attachView(this)
        guardianshipPresenter.subscribe()
        guardianshipPresenter.loadAutoCompleteList()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guardianship_auto_complete_layout)
        ButterKnife.bind(this)
        initToolBar()
        initSearch()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                guardianshipPresenter.loadAutoCompleteList()
            }

            override fun onRequestPermission() {

            }
        })

    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }


    /*Presenter Guardianship started*/
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

    override fun onGuardianshipAutoCompleteListLoaded(guardianshipAutoCompleteList: MutableList<GuardianshipAutoComplete>) {
        searchView.showSearch()
        searchView.setAdapter(GuardianshipAutoCompleteListViewAdapter(context = baseContext,
                guardianshipAutoCompleteList = guardianshipAutoCompleteList,
                onGuardianshipSelectListener = this@GuardianshipAutoCompleteActivity))
    }
    /*presenter Guardianship ended*/

    override fun onGuardianshipSelect(guardianshipAutoComplete: GuardianshipAutoComplete) {
        RxBus.publish(MessageEvent(EventActions.GuardianshipAutoCompleteActivity_Tag, guardianshipAutoComplete))
        finish()
    }

}