package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.DoctorAutoCompleteListViewAdapter
import com.joyBox.shefaa.di.component.DaggerAppointmentReserveComponent
import com.joyBox.shefaa.di.module.AppointmentListPatientModule
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.networking.NetworkingHelper
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject


class AppointmentReserveActivity : BaseActivity(), DoctorContract.View {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.doctorAutoCompleteEditText)
    lateinit var doctorAutoCompleteEditText: EditText

    @BindView(R.id.searchView)
    lateinit var searchView: MaterialSearchView

    @Inject
    lateinit var doctorPresenter: DoctorPresenter

    fun initToolBar() {
        toolbar.setTitle(R.string.AddAppointment)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initDI() {
        val component = DaggerAppointmentReserveComponent.builder()
                .appointmentListPatientModule(AppointmentListPatientModule(this))
                .doctorModule(DoctorModule(this))
                .build()
        component.inject(this)
        doctorPresenter.attachView(this)
        doctorPresenter.subscribe()
        doctorPresenter.loadDoctorAutoComplete(NetworkingHelper.Doctors_Short_List_ServiceUrl)

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
                doctorAutoCompleteEditText.setText((if (query != null) query else ""))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
//                if (newText != null)
//                    doctorPresenter.loadDoctorAutoComplete(NetworkingHelper.Doctors_Short_List_ServiceUrl)
                return false
            }
        })



    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.appointment_reserve_layout)
        ButterKnife.bind(this)

        initToolBar()
        initSearch()
        initDI()


    }

    @OnClick(R.id.doctorAutoCompleteEditText)
    fun onDoctorAutoCompleteEditTextClick(view: View) {
        searchView.showSearch(true)
        searchView.requestFocus()
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
//            R.id.gifsearch_menu_help -> {
//
//            }
        }
        return true
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }


    /*Doctor Presenter started*/
    override fun showEmptyView(visible: Boolean) {

    }

    override fun showLoadErrorMessage(visible: Boolean) {

    }

    override fun showProgress(show: Boolean) {

    }

    override fun onDoctorAutoCompleteSuccessfully(doctorAutoCompleteList: List<DoctorAutoComplete>) {
        searchView.showSearch()
        searchView.setAdapter(DoctorAutoCompleteListViewAdapter(context = baseContext,
                doctorAutoCompleteList = doctorAutoCompleteList.toMutableList()))

        Log.v("", "")
    }

    override fun onDoctorListLoadedSuccessfully(doctorList: MutableList<Doctor>) {

    }
    /*Doctor Presenter ended*/

}