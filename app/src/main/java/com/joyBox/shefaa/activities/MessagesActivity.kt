package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.MessageAdapter
import com.joyBox.shefaa.di.component.DaggerMessageComponent
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.ui.MessagesContract
import com.joyBox.shefaa.di.ui.MessagesPresenter
import com.joyBox.shefaa.entities.MessageEntity
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepositoy
import javax.inject.Inject

/**
 * Created by Adhamkh on 2018-08-16.
 */
class MessagesActivity : BaseActivity(), MessagesContract.View {

    @Inject
    lateinit var presenter: MessagesPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

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

    fun getUrl(): String {
        var client = UserRepositoy(this).getClient()!!
        return NetworkingHelper.MessageALL + "?recipient_id=" + client.getUser().getUid() +
                "&sess_name=" + client.getSessionName() + "&sess_id=" + client.getSessid() +
                "&token=" + client.getToken()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.messages_layout)
        ButterKnife.bind(this)
        initDI()
        initToolBar()
        initRecyclerView()

    }


    /*Presenter started*/
    override fun showEmptyView(visible: Boolean) {
        super.showEmptyView(visible)
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        super.showLoadErrorMessage(visible)
    }

    override fun onMessagesLoaded(messageList: MutableList<MessageEntity>) {
        recyclerView.adapter = MessageAdapter(this, messageList)
        Log.v("", "")
    }

    override fun showProgress(show: Boolean) {

    }
    /*Presenter ended*/
}