package com.joyBox.shefaa.activities.patient

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.GuardianshipRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerGuardianshipComponenet
import com.joyBox.shefaa.di.module.GuardianshipModule
import com.joyBox.shefaa.di.ui.GuardianshipContract
import com.joyBox.shefaa.di.ui.GuardianshipPresenter
import com.joyBox.shefaa.entities.GuardianshipEntity
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject


class GuardianshipActivity : BaseActivity(), GuardianshipContract.View {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @Inject
    lateinit var presenter: GuardianshipPresenter

    fun initToolBar() {
        toolbar.setTitle(R.string.Guardianship)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(GridDividerDecoration(this))
    }

    fun initDI() {
        val component = DaggerGuardianshipComponenet.builder()
                .guardianshipModule(GuardianshipModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadGuardianshipList(NetworkingHelper.MyGuardiansListUrl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guardianship_layout)
        ButterKnife.bind(this)
        initToolBar()
        initRecyclerView()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadGuardianshipList(NetworkingHelper.MyGuardiansListUrl)
            }

            override fun onRequestPermission() {

            }
        })

        RxBus.listen(MessageEvent::class.java).subscribe {
            when (it.action) {
                EventActions.GuardianshipAutoCompleteActivity_Tag -> {
                    presenter.loadGuardianshipList(NetworkingHelper.MyGuardiansListUrl)
                }
            }
        }

    }

    @OnClick(R.id.guardianshipAddBtn)
    fun onGuardianshipAddButton(view: View) {
        IntentHelper.startGuardianshipAddActivity(this)
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

    override fun onGuardianshipListLoaded(guardianshipList: List<GuardianshipEntity>) {
        recyclerView.adapter = GuardianshipRecyclerViewAdapter(context = baseContext, guardianshipEntityList = guardianshipList)
        Log.v("", "")
    }
    /*Presenter ended*/
}