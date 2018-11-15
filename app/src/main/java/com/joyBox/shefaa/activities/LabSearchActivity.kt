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
import com.joyBox.shefaa.adapters.LabRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerLabSearchComponent
import com.joyBox.shefaa.di.module.LabModule
import com.joyBox.shefaa.di.ui.LabContract
import com.joyBox.shefaa.di.ui.LabPresenter
import com.joyBox.shefaa.entities.Lab
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.filtrations.LabFilter
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject

class LabSearchActivity : BaseActivity(), LabContract.View {

    companion object {
        const val LabSearchActivity_Tag = "LabSearchActivity_Tag"
    }

    @Inject
    lateinit var presenter: LabPresenter

    @BindView(R.id.search_view)
    lateinit var search_view: MaterialSearchView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    fun initToolBar() {
        toolbar.setTitle(R.string.Labs)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initRecyclerView() {
        recyclerView.addItemDecoration(GridDividerDecoration(baseContext))
        recyclerView.layoutManager = LinearLayoutManager(baseContext)
    }

    private fun initDI() {
        val component = DaggerLabSearchComponent.builder()
                .labModule(LabModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

        loadData()
    }

    private fun loadData() {
        val filter: LabFilter = Gson().fromJson(intent.getStringExtra(LabSearchActivity_Tag), LabFilter::class.java)
        var url = NetworkingHelper.Lab_Search_Url
        var concat = "?"
        if (!filter.query.isNullOrBlank()) {
            url += concat + "lab_name=" + filter.query
            concat = "&"
        }
        if (!filter.address.isNullOrBlank()) {
            url += concat + "place=" + filter.query
            concat = "&"
        }
        presenter.loadLabList(url)
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
        setContentView(R.layout.lab_search_layout)
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

    override fun onLabListLoaded(labList: MutableList<Lab>) {
        recyclerView.adapter = LabRecyclerViewAdapter(context = baseContext, labList = labList)
        Log.v("", "")
    }

    /*Presenter ended*/
}