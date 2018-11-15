package com.joyBox.shefaa.activities.autoComplete

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.UserListViewAdapter
import com.joyBox.shefaa.di.component.DaggerUserAutoCompleteComponent
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.ui.MessagesContract
import com.joyBox.shefaa.di.ui.MessagesPresenter
import com.joyBox.shefaa.entities.AutoCompleteUser
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.listeners.OnAutoCompleteUserSelectListener
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.Stateslayoutview
import com.miguelcatalan.materialsearchview.MaterialSearchView
import javax.inject.Inject

class UserAutoCompleteActivity : BaseActivity(), MessagesContract.View, OnAutoCompleteUserSelectListener {

    @BindView(R.id.searchView)
    lateinit var searchView: MaterialSearchView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: MessagesPresenter

    fun initToolBar() {
        toolbar.setTitle(R.string.UserAutoComplete)
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
        val component = DaggerUserAutoCompleteComponent.builder()
                .messageModule(MessageModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadAutoCompleteUsers(NetworkingHelper.All_User_AutoComplete_Url)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_auto_complete_layout)
        ButterKnife.bind(this)
        initToolBar()
        initSearch()
        initDI()


        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadAutoCompleteUsers(NetworkingHelper.All_User_AutoComplete_Url)
            }

            override fun onRequestPermission() {

            }
        })

    }

    override fun onSelectAutoCompleteUser(user: AutoCompleteUser) {
        RxBus.publish(MessageEvent(EventActions.UserAutoCompleteActivity_Tag, user))
        finish()
        Log.v("", "")
    }

    override fun onBackPressed() {
        if (searchView.isSearchOpen) {
            searchView.closeSearch()
        } else {
            super.onBackPressed()
        }
    }

    /*User presenter started*/
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

    override fun onAutoCompleteUsersLoaded(messageList: MutableList<AutoCompleteUser>) {
        searchView.showSearch()
        searchView.setAdapter(UserListViewAdapter(context = baseContext,
                userAutoCompleteList = messageList,
                onUserSelectListener = this@UserAutoCompleteActivity))
        Log.v("", "")
    }
    /*User presenter ended*/


}