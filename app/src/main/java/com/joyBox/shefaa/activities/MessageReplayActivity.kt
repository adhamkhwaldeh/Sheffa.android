package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.di.component.DaggerMessageReplayComponent
import com.joyBox.shefaa.di.module.MessageReplayModule
import com.joyBox.shefaa.di.ui.MessageReplayContract
import com.joyBox.shefaa.di.ui.MessageReplayPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.entities.MessageResult
import com.joyBox.shefaa.entities.models.MessageReplayModel
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import javax.inject.Inject

class MessageReplayActivity : BaseActivity(), MessageReplayContract.View {

    companion object {
        val ReplayMessageActivity_Tag = "ReplayMessageActivity_Tag"
    }

    var progressDialog: ProgressDialog = ProgressDialog()

    @Inject
    lateinit var presenter: MessageReplayPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tomessagetxt)
    lateinit var tomessagetxt: EditText

    @BindView(R.id.subjecttxt)
    lateinit var subjecttxt: EditText

    @BindView(R.id.bodytxt)
    lateinit var bodytxt: EditText

    @BindView(R.id.submitbtn)
    lateinit var submitbtn: Button

    lateinit var messageReplayModel: MessageReplayModel


    private fun initDI() {
        val component = DaggerMessageReplayComponent.builder()
                .messageReplayModule(MessageReplayModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    private fun initToolBar() {
        toolbar.setTitle(R.string.Reply)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.replay_message_layout)
        ButterKnife.bind(this)
        val json = intent.getStringExtra(ReplayMessageActivity_Tag)
        messageReplayModel = Gson().fromJson(json, MessageReplayModel::class.java)
        initDI()
        initToolBar()
    }

    @OnClick(R.id.submitbtn)
    fun OnSubmitButtonClick(view: View) {
        generateRequest()
    }

    fun generateRequest() {
        var client = UserRepository(this).getClient()!!
        var url = NetworkingHelper.ReplayMessageUrl + "?recipients_ids=" +
                messageReplayModel.TargetId + "&subject=" + subjecttxt.getText().toString() + "&body=" +
                bodytxt.getText().toString() + "&sess_id=" + client.getSessid() +
                "&sess_name=" + client.getSessionName() + "&token=" + client.getToken();
        presenter.submitReplay(url)
    }

    /*Presenter started*/
    override fun onMessagesLoadedSuccesfuly() {
        Toast.makeText(applicationContext, R.string.SendMessageSuccessfuly, Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onMessagesLoadedFail(messageResult: MessageResult) {
        Toast.makeText(applicationContext, messageResult.getMessages().error.get(0), Toast.LENGTH_LONG).show()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressDialog.show(supportFragmentManager, ProgressDialog.ProgressDialog_Tag)
        } else {
            progressDialog.dismiss()
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        Toast.makeText(applicationContext, R.string.ErrorConnection, Toast.LENGTH_LONG).show()
    }
    /*Presenter ended*/

}