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
import com.joyBox.shefaa.adapters.MessageRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerMessageComponent
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.ui.MessagesContract
import com.joyBox.shefaa.di.ui.MessagesPresenter
import com.joyBox.shefaa.entities.MessageEntity
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class MessagesActivity : BaseActivity(), MessagesContract.View {

    @Inject
    lateinit var presenter: MessagesPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    private fun initDI() {
        val component = DaggerMessageComponent.builder()
                .messageModule(MessageModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadMessages(getUrl())
    }

    private fun initToolBar() {
        toolbar.setTitle(R.string.Messages)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getUrl(): String {
        val client = UserRepository(this).getClient()!!
        return NetworkingHelper.MessageALL + "?recipient_id=" + client.user.uid +
                "&sess_name=" + client.sessionName + "&sess_id=" + client.sessid +
                "&token=" + client.token
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.messages_layout)
        ButterKnife.bind(this)
        initToolBar()
        initRecyclerView()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadMessages(getUrl())
            }

            override fun onRequestPermission() {

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.message_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sendMessage -> {
                IntentHelper.startMessageAddActivity(this@MessagesActivity)
            }
        }
        return super.onOptionsItemSelected(item)
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

    override fun onMessagesLoaded(messageList: MutableList<MessageEntity>) {
        recyclerView.adapter = MessageRecyclerViewAdapter(this, messageList)
        Log.v("", "")
    }

    /*Presenter ended*/

}