package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.NotificationRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerNotificationComponent
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.ui.NotificationContract
import com.joyBox.shefaa.di.ui.NotificationPresenter
import com.joyBox.shefaa.entities.NotificationEntity
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

/**
 * Created by Adhamkh on 2018-08-15.
 */
class NotificationsActivity : BaseActivity(), NotificationContract.View {

    @Inject
    lateinit var presenter: NotificationPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    private fun initDI() {
        val component = DaggerNotificationComponent.builder()
                .notificationModule(NotificationModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadNotifications()
    }

    private fun initToolBar() {
        toolbar.setTitle(R.string.Notifications)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(GridDividerDecoration(this))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notification_layout)
        ButterKnife.bind(this)
        initDI()
        initToolBar()
        initRecyclerView()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadNotifications()
            }

            override fun onRequestPermission() {

            }
        })

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

    override fun onNotificationsLoaded(notifications: MutableList<NotificationEntity>) {
        recyclerView.adapter = NotificationRecyclerViewAdapter(this, notifications)
        Log.v("", "")
    }


    override fun onRegisterTokenSuccessfully() {

    }

    override fun onRegisterTokenFail() {

    }
    /*Presenter ended*/

}