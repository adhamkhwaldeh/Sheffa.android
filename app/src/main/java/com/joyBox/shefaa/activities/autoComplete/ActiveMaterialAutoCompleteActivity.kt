package com.joyBox.shefaa.activities.autoComplete

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.ActiveMaterialAutoCompleteListViewAdapter
import com.joyBox.shefaa.adapters.MedicineAutoCompleteListViewAdapter
import com.joyBox.shefaa.di.component.DaggerActiveMaterialAutoCompleteComponent
import com.joyBox.shefaa.di.component.DaggerMedicineAutoCompleteComponent
import com.joyBox.shefaa.di.module.MedicineModule
import com.joyBox.shefaa.di.ui.MedicineContract
import com.joyBox.shefaa.di.ui.MedicinePresenter
import com.joyBox.shefaa.entities.ActiveMaterialAutoComplete
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnActiveMaterialSelectListener
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject


class ActiveMaterialAutoCompleteActivity : BaseActivity(), MedicineContract.View, OnActiveMaterialSelectListener {

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
        val component = DaggerActiveMaterialAutoCompleteComponent.builder()
                .medicineModule(MedicineModule(this))
                .build()
        component.inject(this)
        medicinePresenter.attachView(this)
        medicinePresenter.subscribe()
        medicinePresenter.loadActiveMaterialList()
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
                medicinePresenter.loadActiveMaterialList()
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

    override fun onActiveMaterialListLoadedSuccessfully(activeMaterialAutoCompleteList: MutableList<ActiveMaterialAutoComplete>) {
        searchView.showSearch()
        searchView.setAdapter(ActiveMaterialAutoCompleteListViewAdapter(context = baseContext,
                medicineAutoCompleteList = activeMaterialAutoCompleteList,
                onActiveMaterialSelectListener = this@ActiveMaterialAutoCompleteActivity))

        Log.v("", "")
    }


    /*Doctor Presenter ended*/

    override fun onActiveMaterialSelect(activeMaterialAutoComplete: ActiveMaterialAutoComplete) {
        RxBus.publish(MessageEvent(EventActions.ActiveMaterialAutoCompleteActivity_Tag, activeMaterialAutoComplete))
        finish()
        Log.v("", "")
    }


}