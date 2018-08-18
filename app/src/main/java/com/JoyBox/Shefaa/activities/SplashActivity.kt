package com.joyBox.shefaa.activities

import android.os.Bundle
import android.widget.Toast
import com.JoyBox.Shefaa.R
//import com.google.firebase.iid.FirebaseInstanceId
//import com.google.firebase.messaging.FirebaseMessaging
import com.joyBox.shefaa.di.component.DaggerSplashComponent
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.ui.NotificationPresenter
import com.joyBox.shefaa.di.ui.RegistrationContract
import com.joyBox.shefaa.di.ui.RegistrationPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.models.LoginModel
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.repositories.UserRepositoy
import javax.inject.Inject

class SplashActivity : BaseActivity(), RegistrationContract.View {

    @Inject
    lateinit var presenter: RegistrationPresenter

    @Inject
    lateinit var notificationPresenter: NotificationPresenter

    private fun initDI() {
        val component = DaggerSplashComponent.builder()
                .registrationModule(RegistrationModule(this))
                .notificationModule(NotificationModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    var progressDialog: ProgressDialog = ProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_layout)
        initDI()

        val client = UserRepositoy(this).getClient()
        val loginModel = UserRepositoy(this).getLoginModel()
        if (client == null || loginModel == null) {
            IntentHelper.startSignInActivity(this)
        } else {
            presenter.logIn(loginModel)
        }
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {
        if (show)
            progressDialog.show(supportFragmentManager, ProgressDialog.ProgressDialog_Tag)
        else
            progressDialog.dismiss()
    }

    override fun loginSuccessfully(client: Client) {
        UserRepositoy(this).putClient(client)

//        FirebaseMessaging.getInstance().subscribeToTopic(client.getUser().getUid())
//        var token: String = FirebaseInstanceId.getInstance().getToken()!!
//        notificationPresenter.registerToken(client, token)
        Toast.makeText(baseContext, resources.getString(R.string.Welcome), Toast.LENGTH_LONG).show()
        IntentHelper.startMainActivity(this)
        finish()
    }

    override fun loginFail() {
        Toast.makeText(baseContext, resources.getString(R.string.usernameorpasswordError), Toast.LENGTH_LONG).show();
        IntentHelper.startSignInActivity(this)
        finish()
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        Toast.makeText(baseContext, resources.getString(R.string.ErrorConnection), Toast.LENGTH_LONG).show();
        IntentHelper.startSignInActivity(this)
        finish()
    }
    /*Presenter ended*/
}