package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.entities.MessageEntity
import com.joyBox.shefaa.entities.models.MessageReplayModel
import com.joyBox.shefaa.helpers.IntentHelper

/**
 * Created by Adhamkh on 2018-08-16.
 */
class MessageDetailsActivity : BaseActivity() {

    companion object {
        val MessageDetailsActivity_Tag = "MessageDetailsActivity_Tag"
    }

    lateinit var message: MessageEntity

    @BindView(R.id.messagedate)
    lateinit var messagedate: TextView

    @BindView(R.id.body)
    lateinit var body: TextView

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    fun initToolBar() {
        toolbar.setTitle(R.string.Messages)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.message_details_layout)
        ButterKnife.bind(this)
        message = Gson().fromJson(intent.extras[MessageDetailsActivity_Tag].toString(), MessageEntity::class.java)
        initToolBar()
        bindingdata()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_message_details, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.sendmessage -> {
                IntentHelper.startReplayMessageActivity(this, MessageReplayModel(message.author.uid, message.author.name));
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun bindingdata() {
        messagedate.text = message.getDate()
        body.setText(message.body)
    }

}