package com.joyBox.shefaa.activities.patient

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Spinner
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.IndicatorSpinnerAdapter
import com.joyBox.shefaa.adapters.SelfMonitorRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerSelfMonitorComponent
import com.joyBox.shefaa.di.module.SelfMonitorModule
import com.joyBox.shefaa.di.ui.SelfMonitorContact
import com.joyBox.shefaa.di.ui.SelfMonitorPresenter
import com.joyBox.shefaa.entities.SelfMonitorEntity
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepositoy
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import io.reactivex.internal.schedulers.NewThreadWorker
import javax.inject.Inject


class SelfMonitorActivity : BaseActivity(), SelfMonitorContact.View {


    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.indicatorSpinner)
    lateinit var indicatorSpinner: Spinner

    @Inject
    lateinit var presenter: SelfMonitorPresenter


    fun initToolBar() {
        toolbar.setTitle(R.string.SelfMonitor)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(GridDividerDecoration(this))
    }

    fun initDI() {
        val component = DaggerSelfMonitorComponent.builder()
                .selfMonitorModule(SelfMonitorModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadSelfMonitorList(NetworkingHelper.SelfMonitorListUrl)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.self_monitor_layout)
        ButterKnife.bind(this)

        initToolBar()
        initRecyclerView()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadSelfMonitorList(NetworkingHelper.SelfMonitorListUrl)
            }

            override fun onRequestPermission() {

            }
        })

    }

    @OnClick(R.id.SelfMonitorAddBtn)
    fun onSelfMonitorAddButtonClick(view: View) {
        IntentHelper.startSelfMonitorAddActivity(this)
    }

    /*presenter started*/
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

    override fun onSelfMonitorListLoaded(selfMonitorList: List<SelfMonitorEntity>) {

        val indicatorList: MutableList<String> = selfMonitorList
                .flatMap { it -> it.indicator.split(",") }.toMutableList()

        indicatorSpinner.adapter = IndicatorSpinnerAdapter(context = baseContext, indicatorList = indicatorList)
        recyclerView.adapter = SelfMonitorRecyclerViewAdapter(context = baseContext, selfMonitorList = selfMonitorList)
        Log.v("", "")

    }
    /*presenter ended*/
}