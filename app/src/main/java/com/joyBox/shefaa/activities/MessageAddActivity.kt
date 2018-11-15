package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.UserRecyclerViewAdapter
import com.joyBox.shefaa.di.ui.MessagesContract
import com.joyBox.shefaa.entities.AutoCompleteUser
import com.joyBox.shefaa.entities.models.MessageReplayModel
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper


class MessageAddActivity : BaseActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.usersRecyclerView)
    lateinit var usersRecyclerView: RecyclerView

    private fun initToolBar() {
        toolbar.setTitle(R.string.SendMessage)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun initRecyclerView() {
        usersRecyclerView.layoutManager = LinearLayoutManager(baseContext, RecyclerView.HORIZONTAL, false)
        usersRecyclerView.adapter = UserRecyclerViewAdapter(baseContext, mutableListOf(), null, false)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_add_layout)
        ButterKnife.bind(this)

        initToolBar()
        initRecyclerView()

        RxBus.listen(MessageEvent::class.java).subscribe({

            when (it.action) {
                EventActions.UserAutoCompleteActivity_Tag -> {
                    val user = it.message as AutoCompleteUser
                    (usersRecyclerView.adapter as UserRecyclerViewAdapter).addUser(user)
                    Log.v("", "")
                }
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.message_add_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.addPerson -> {
                IntentHelper.startUserAutoCompleteActivity(baseContext)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @OnClick(R.id.sendButton)
    fun onSendButtonClick(view: View) {

        val list = (usersRecyclerView.adapter as UserRecyclerViewAdapter).usersList
        if (list.size > 0) {
            var targetId = list.joinToString { it.uid }
            targetId = targetId.removeSuffix(",")
            IntentHelper.startReplayMessageActivity(baseContext, MessageReplayModel(targetId, ""))
        }

    }


}