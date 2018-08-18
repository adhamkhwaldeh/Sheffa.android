package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.models.LoginModel
import com.joyBox.shefaa.entities.models.SignUpPostModel

/**
 * Created by Adhamkh on 2018-08-10.
 */
class RegistrationContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun logIn(loginModel: LoginModel)
        fun signUp(signUpPostModel: SignUpPostModel)
        fun forgotPassword(email: String)
    }

    interface View : BaseContract.View {
        fun loginSuccessfully(client: Client) {}
        fun loginFail() {}
//        fun loginNoInternetConnection()

        fun signUpFail() {}
        fun SignUpSuccessfully() {}

        fun forgotPasswordFail() {}
        fun forgotPasswordSuccessfully() {}

    }

}