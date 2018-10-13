package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.MainProfile
import com.joyBox.shefaa.entities.PatientProfile
import com.joyBox.shefaa.entities.models.LoginModel
import com.joyBox.shefaa.entities.models.SignUpPostModel
import com.joyBox.shefaa.enums.Gender
import com.joyBox.shefaa.enums.ProfileType

/**
 * Created by Adhamkh on 2018-08-10.
 */
class RegistrationContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun logIn(loginModel: LoginModel)
        fun signUp(signUpPostModel: SignUpPostModel)
        fun forgotPassword(email: String)
        fun changeEmail(userId: String, newEmail: String, currentPassword: String)
        fun changePassword(userId: String, newPassword: String, currentPassword: String)

        fun logout(url: String)


        fun loadMainProfile(userId: String)
        fun loadPatientProfile(userId: String)

        fun updateMainProfile(uid: String, profileType: ProfileType, first_name: String,
                              last_name: String, gender: String)

    }

    interface View : BaseContract.View {
        fun loginSuccessfully(client: Client) {}
        fun loginFail() {}

        fun signUpFail() {}
        fun signUpSuccessfully() {}


        //        fun forgotPasswordWaiting() {}
//        fun forgotPasswordFailNoConnection() {}
        fun forgotPasswordSuccessfully() {}

        fun forgotPasswordFail() {}

        fun changeEmailSuccessfully() {}
        fun changeEmailFail() {}

        fun changePasswordSuccessfully() {}
        fun changePasswordFail() {}

        fun logoutSuccessfully() {}
        fun logoutFail() {}


        fun mainProfileFail() {}
        fun mainProfileSuccessfully(mainProfile: MainProfile) {}

        fun patientProfileFail() {}
        fun patientProfileSuccessfully(patientProfile: PatientProfile) {}

        fun mainProfileUpdateFail() {}
        fun mainProfileUpdateSuccessfully() {}

    }

}