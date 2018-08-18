package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.models.LoginModel
import com.joyBox.shefaa.entities.models.SignUpPostModel
import com.joyBox.shefaa.networking.listeners.OnForgotResponseListener
import com.joyBox.shefaa.networking.listeners.OnSignInResponseListener
import com.joyBox.shefaa.networking.listeners.OnSignUpResponseListener
import com.joyBox.shefaa.networking.tasks.ForgotPasswordAsync
import com.joyBox.shefaa.networking.tasks.SignUpAsync
import com.joyBox.shefaa.networking.tasks.SigninAsync
import io.reactivex.disposables.CompositeDisposable

class RegistrationPresenter constructor(val context: Context) : RegistrationContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: RegistrationContract.View

    init {

    }

    override fun subscribe() {

    }

    override fun attachView(view: RegistrationContract.View) {
        this.view = view
    }

    override fun unSubscribe() {
        subscriptions.clear()
    }

    override fun logIn(loginModel: LoginModel) {
        SigninAsync(loginModel, object : OnSignInResponseListener {
            override fun SignInLoading() {
                view.showProgress(true)
            }

            override fun SignInFail() {
                view.showProgress(false)
                view.loginFail()
            }

            override fun SignInSuccessFuly(client: Client) {
                view.showProgress(false)
                view.loginSuccessfully(client)
            }

            override fun SignInInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }
        }).execute()
    }

    override fun signUp(signUpPostModel: SignUpPostModel) {
        SignUpAsync(signUpPostModel, object : OnSignUpResponseListener {
            override fun SignUpLoading() {
                view.showProgress(true)
            }

            override fun SignUpFail() {
                view.showProgress(false)
                view.signUpFail()
            }

            override fun SignUpSuccessFuly() {
                view.showProgress(false)
                view.SignUpSuccessfully()
            }

            override fun SignUpInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }
        }).execute()
    }

    override fun forgotPassword(email: String) {
        ForgotPasswordAsync(email, object : OnForgotResponseListener {
            override fun ForgotPasswordLoading() {
                view.showProgress(true)
            }

            override fun ForgotPasswordFail() {
                view.showProgress(false)
                view.forgotPasswordFail()
            }

            override fun ForgotPasswordSuccessFuly() {
                view.showProgress(false)
                view.forgotPasswordSuccessfully()
            }

            override fun ForgotPasswordInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }
        }).execute()
    }

}