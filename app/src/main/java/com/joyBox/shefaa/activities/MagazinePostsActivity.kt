package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.MagazinePostRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerMagazinePostsComponent
import com.joyBox.shefaa.di.module.MagazinePostModule
import com.joyBox.shefaa.di.ui.MagazinePostsContract
import com.joyBox.shefaa.di.ui.MagazinePostsPresenter
import com.joyBox.shefaa.entities.MagazinePost
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class MagazinePostsActivity : BaseActivity(), MagazinePostsContract.View {

    @Inject
    lateinit var presenter: MagazinePostsPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    private fun initDI() {
        val component = DaggerMagazinePostsComponent.builder()
                .magazinePostModule(MagazinePostModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadMagazinePostsList(NetworkingHelper.MagazinePostsUrl)
    }

    private fun initToolBar() {
        toolbar.setTitle(R.string.Magazine)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(GridDividerDecoration(this))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.magazine_layout)
        ButterKnife.bind(this)
        initToolBar()
        initRecyclerView()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadMagazinePostsList(NetworkingHelper.MagazinePostsUrl)
            }

            override fun onRequestPermission() {

            }
        })

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

    override fun onMagazinePostsSuccessfully(magazinePostsList: List<MagazinePost>) {
        recyclerView.adapter = MagazinePostRecyclerViewAdapter(baseContext, magazinePostsList)
        Log.v("", "")
    }

    /*presenter ended*/
}