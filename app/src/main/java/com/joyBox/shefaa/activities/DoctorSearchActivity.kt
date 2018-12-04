package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.adapters.DoctorRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorSearchComponent
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.filtrations.DoctorFilter
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject

class DoctorSearchActivity : BaseActivity(), DoctorContract.View {

    companion object {
        const val DoctorSearchActivity_Tag = "DoctorSearchActivity_Tag"
    }

    @Inject
    lateinit var presenter: DoctorPresenter

    @BindView(R.id.search_view)
    lateinit var search_view: MaterialSearchView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    fun initToolBar() {
        toolbar.setTitle(R.string.Doctors)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initRecyclerView() {
        recyclerView.addItemDecoration(GridDividerDecoration(baseContext))
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
    }

    private fun initDI() {
        val component = DaggerDoctorSearchComponent.builder()
                .doctorModule(DoctorModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

        loadData()

    }

    private fun loadData() {
        val filter: DoctorFilter = Gson().fromJson(intent.getStringExtra(DoctorSearchActivity_Tag), DoctorFilter::class.java)
        var url = NetworkingHelper.Doctor_Search_Url
        var concat = "?"
        if (!filter.query.isNullOrBlank()) {
//            url += concat + "doctor_name=" + filter.query
//            concat = "&"
            url += concat + "first_name=" + filter.query
            concat = "&"
            url += concat + "last_name=" + filter.query
            concat = "&"
        }
        if (!filter.cost.isNullOrBlank()) {
            url += concat + "field_cost_value=" + filter.cost
            concat = "&"
        }
        if (filter.city.isNullOrBlank()) {
            url += concat + "city=" + filter.city
            concat = "&"
        }
        if (filter.specialistAutoComplete != null) {
            url += concat + "field_doctor_specialization_tid=" + filter.specialistAutoComplete!!.tid
            concat = "&"
        }
        presenter.loadDoctorList(url)
    }

    fun initSearch() {
        search_view.setSubmitOnClick(true)
        search_view.setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
//                KeywordValue.text = query
//                if (KeywordValue.text.isNotEmpty())
//                    KeywordValue.visibility = View.VISIBLE
//                else
//                    KeywordValue.visibility = View.GONE
//                clearData()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                try {
                    if (newText != null) {
                        (recyclerView.adapter as DoctorRecyclerViewAdapter).filter.filter(newText)
                    }
                } catch (ex: Exception) {
                }
                return false
            }
        })
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_search_layout)
        ButterKnife.bind(this)
        initToolBar()
        initRecyclerView()
        initDI()

        initSearch()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                loadData()
//                presenter.loadDoctorList(NetworkingHelper.Doctor_Search_Url)
            }

            override fun onRequestPermission() {

            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.doctor_search_menu, menu)
        val item = menu?.findItem(R.id.gifsearch_menu_search)
        search_view.setMenuItem(item)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.map_search -> {
                val activityName = DoctorSearchMapActivity::class.java.canonicalName
                IntentHelper.startLocationCheckActivity(this, activityName)
//                Log.v("View Clicked", view.id.toString())
            }
        }
        return true
    }


    override fun onBackPressed() {
        if (search_view.isSearchOpen) {
            search_view.closeSearch()
        } else {
            super.onBackPressed()
        }
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
//        search_view.setSuggestions(doctorAutoCompleteListresults.toTypedArray())
        Log.v("", "")
    }

    override fun onDoctorListLoadedSuccessfully(doctorList: MutableList<Doctor>) {
        recyclerView.adapter = DoctorRecyclerViewAdapter(context = baseContext, doctorList = doctorList)
        Log.v("", "")
    }

    /*Presenter ended*/
}