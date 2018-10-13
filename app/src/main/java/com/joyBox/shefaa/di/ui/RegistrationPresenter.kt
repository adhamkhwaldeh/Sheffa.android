package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.MainProfile
import com.joyBox.shefaa.entities.PatientProfile
import com.joyBox.shefaa.entities.models.LoginModel
import com.joyBox.shefaa.entities.models.SignUpPostModel
import com.joyBox.shefaa.enums.Gender
import com.joyBox.shefaa.enums.ProfileType
import com.joyBox.shefaa.networking.listeners.*
import com.joyBox.shefaa.networking.tasks.*
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
                view.signUpSuccessfully()
            }

            override fun SignUpInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }
        }).execute()
    }

    override fun forgotPassword(email: String) {
        ForgotPasswordAsync(email, object : OnForgotResponseListener {
            override fun forgotPasswordLoading() {
                view.showProgress(true)
            }

            override fun forgotPasswordFail() {
                view.showProgress(false)
                view.forgotPasswordFail()
            }

            override fun forgotPasswordSuccessFuly() {
                view.showProgress(false)
                view.forgotPasswordSuccessfully()
            }

            override fun forgotPasswordInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }
        }).execute()
    }

    override fun changeEmail(userId: String, newEmail: String, currentPassword: String) {
        ChangeEmailAsync(userId, newEmail, currentPassword, object : OnChangeEmailListener {
            override fun changeEmailLoading() {
                view.showProgress(true)
            }

            override fun changeEmailFail() {
                view.showProgress(false)
                view.changeEmailFail()
            }

            override fun changeEmailSuccessFully() {
                view.showProgress(false)
                view.changeEmailSuccessfully()
            }

            override fun changeEmailInternetConnection() {
                view.showLoadErrorMessage(true)
            }
        }).execute()
    }

    override fun changePassword(userId: String, newPassword: String, currentPassword: String) {
        ChangePasswordAsync(userId, newPassword, currentPassword, object : OnChangePasswordListener {
            override fun changePasswordLoading() {
                view.showProgress(true)
            }

            override fun changePasswordFail() {
                view.showProgress(false)
                view.changePasswordFail()
            }

            override fun changePasswordSuccessFully() {
                view.showProgress(false)
                view.changePasswordSuccessfully()
            }

            override fun changePasswordInternetConnection() {
                view.showLoadErrorMessage(true)
            }
        }).execute()
    }

    override fun logout(url: String) {
        LogoutAsync(url, object : OnLogoutListener {
            override fun onLogoutLoading() {
                view.showProgress(true)
            }

            override fun onLogoutInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onLogoutSuccessFully() {
                view.logoutSuccessfully()
            }

            override fun onLogoutFail() {
                view.logoutFail()
            }
        }).execute()
    }



    override fun loadMainProfile(userId: String) {
        MainProfileAsync(userId, object : OnMainProfileListener {
            override fun onMainProfileLoading() {
                view.showProgress(true)
            }

            override fun onMainProfileInternetConnection() {
                view.showProgress(false)
                view.mainProfileFail()
            }

            override fun onMainProfileSuccessFully(mainProfile: MainProfile) {
                view.showProgress(false)
                view.mainProfileSuccessfully(mainProfile)
            }

            override fun onMainProfileNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun loadPatientProfile(userId: String) {
        PatientProfileAsync(userId, object : OnPatientProfileListener {
            override fun onPatientProfileoading() {
                view.showProgress(true)
            }

            override fun onPatientProfileInternetConnection() {
                view.showProgress(false)
                view.patientProfileFail()
            }

            override fun onPatientProfileSuccessFully(patientProfile: PatientProfile) {
                view.showProgress(false)
                view.patientProfileSuccessfully(patientProfile)
            }

            override fun onPatientProfileNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }

        }).execute()

    }

    override fun updateMainProfile(uid: String, profileType: ProfileType, first_name: String, last_name: String, gender: String) {
        MainProfileUpdateAsync(uid, profileType, first_name, last_name, gender, object : OnMainProfileUpdateListener {
            override fun onMainProfileUpdateLoading() {
                view.showProgress(true)
            }

            override fun onMainProfileUpdateInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onMainProfileUpdateSuccessFully() {
                view.showProgress(false)
                view.mainProfileUpdateSuccessfully()
            }

            override fun onMainProfileUpdateFail() {
                view.showProgress(false)
                view.mainProfileUpdateFail()
            }
        }).execute()
    }

}