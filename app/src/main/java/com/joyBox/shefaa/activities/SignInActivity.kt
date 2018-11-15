package com.joyBox.shefaa.activities

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
//import com.google.firebase.iid.FirebaseInstanceId
//import com.google.firebase.messaging.FirebaseMessaging
import com.joyBox.shefaa.di.component.DaggerSignInComponent
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.ui.NotificationContract
import com.joyBox.shefaa.di.ui.NotificationPresenter
import com.joyBox.shefaa.di.ui.RegistrationContract
import com.joyBox.shefaa.di.ui.RegistrationPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.NotificationEntity
import com.joyBox.shefaa.entities.models.LoginModel
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.repositories.UserRepository
import javax.inject.Inject


class SignInActivity : BaseActivity(), RegistrationContract.View, NotificationContract.View {

    @Inject
    lateinit var presenter: RegistrationPresenter

    @Inject
    lateinit var notificationPresenter: NotificationPresenter

    @BindView(R.id.userNameEditText)
    lateinit var userNameEditText: EditText

    @BindView(R.id.passwordEditText)
    lateinit var passwordEditText: EditText

    @BindView(R.id.toolBarTitle)
    lateinit var toolBarTitle: TextView

    var progressDialog: ProgressDialog = ProgressDialog()

    private fun initDI() {
        val component = DaggerSignInComponent.builder()
                .registrationModule(RegistrationModule(this))
                .notificationModule(NotificationModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

        notificationPresenter.attachView(this)
        presenter.subscribe()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin_layout)
        ButterKnife.bind(this)
        initDI()

        toolBarTitle.setText(R.string.SignIn)

    }

    @OnClick(R.id.signUpText)
    fun onSignUpClick(view: View) {
        IntentHelper.startSignUpActivity(this)
    }

    @OnClick(R.id.forgotPasswordText)
    fun onForgotPasswordTextClick(view: View) {
        IntentHelper.startForgotPasswordActivity(this)
    }

    @OnClick(R.id.signinbtn)
    fun onSignInBtnClick(view: View) {
        presenter.logIn(LoginModel(userName = userNameEditText.text.toString(),
                password = passwordEditText.text.toString()))
    }

    override fun showProgress(show: Boolean) {
        if (show)
            progressDialog.show(supportFragmentManager, ProgressDialog.ProgressDialog_Tag)
        else
            progressDialog.dismiss()
    }

    override fun loginSuccessfully(client: Client) {
        UserRepository(this).putClient(client)

        UserRepository(this).putLoginModel(LoginModel(userName = userNameEditText.text.toString(),
                password = passwordEditText.text.toString()))

        FirebaseMessaging.getInstance().subscribeToTopic(client.user.uid)
        val token: String = FirebaseInstanceId.getInstance().token!!
        notificationPresenter.registerToken(client, token)

        Toast.makeText(baseContext, resources.getString(R.string.Welcome), Toast.LENGTH_LONG).show()
        IntentHelper.startMainActivity(this)
        finish()
    }

    override fun loginFail() {
        Toast.makeText(baseContext, resources.getString(R.string.usernameorpasswordError), Toast.LENGTH_LONG).show();
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        Toast.makeText(baseContext, resources.getString(R.string.ErrorConnection), Toast.LENGTH_LONG).show();
    }


    override fun onNotificationsLoaded(notifications: MutableList<NotificationEntity>) {

    }

    override fun onRegisterTokenSuccessfully() {

    }

    override fun onRegisterTokenFail() {

    }

}