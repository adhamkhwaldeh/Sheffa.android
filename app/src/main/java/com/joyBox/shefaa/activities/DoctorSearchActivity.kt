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
import com.joyBox.shefaa.adapters.DoctorRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorSearchComponent
import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.ui.DoctorContract
import com.joyBox.shefaa.di.ui.DoctorPresenter
import com.joyBox.shefaa.entities.Doctor
import com.joyBox.shefaa.entities.DoctorAutoComplete
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject

class DoctorSearchActivity : BaseActivity(), DoctorContract.View {

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
        presenter.loadDoctorList(NetworkingHelper.Doctor_Search_Url)
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
//                if (newText != null)
//                    presenter.LoadSuggestion(newText)
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

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadDoctorList(NetworkingHelper.Doctor_Search_Url)
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
//            R.id.gifsearch_menu_help -> {
//
//            }
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