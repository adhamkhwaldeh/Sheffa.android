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
import com.joyBox.shefaa.adapters.PharmacyRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerPharmacySearchComponent
import com.joyBox.shefaa.di.module.PharmacyModule
import com.joyBox.shefaa.di.ui.PharmacyPresenter
import com.joyBox.shefaa.di.ui.PharmacyContract
import com.joyBox.shefaa.entities.Pharmacy
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.filtrations.PharmacyFilter
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject

class PharmacySearchActivity : BaseActivity(), PharmacyContract.View {

    companion object {
        const val PharmacySearchActivity_Tag = "PharmacySearchActivity_Tag"
    }

    @Inject
    lateinit var presenter: PharmacyPresenter

    @BindView(R.id.search_view)
    lateinit var search_view: MaterialSearchView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    fun initToolBar() {
        toolbar.setTitle(R.string.Pharmacies)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initRecyclerView() {
        recyclerView.addItemDecoration(GridDividerDecoration(baseContext))
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
    }

    private fun initDI() {
        val component = DaggerPharmacySearchComponent.builder()
                .pharmacyModule(PharmacyModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

        loadData()
    }

    private fun loadData() {
        val filter: PharmacyFilter = Gson().fromJson(
                intent.getStringExtra(PharmacySearchActivity_Tag), PharmacyFilter::class.java)
        var url = NetworkingHelper.Pharmacy_Search_Url
        var concat = "?"

        if (!filter.query.isNullOrBlank()) {
            url += concat + "pharmacy_name=" + filter.query
            concat = "&"
        }
        if (!filter.medicineName.isNullOrBlank()) {
            url += concat + "med_name=" + filter.medicineName
            concat = "&"
        }
        presenter.loadPharmacyList(url)
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
        setContentView(R.layout.pharmacy_search_layout)
        ButterKnife.bind(this)
        initToolBar()
        initRecyclerView()
        initDI()


        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                loadData()
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


    override fun onPharmacyListLoadedSuccessfully(pharmacyList: MutableList<Pharmacy>) {
        recyclerView.adapter = PharmacyRecyclerViewAdapter(context = baseContext, pharmacyList = pharmacyList)
        Log.v("", "")
    }
    /*Presenter ended*/

}