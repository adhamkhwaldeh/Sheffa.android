package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.MedicalProfile


class MedicalProfileContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadUserProfile(userId: String, profileType: String)

        fun updateUserProfile(url: String)
    }

    interface View : BaseContract.View {
        fun onUserProfileLoaded(medicalProfile: MedicalProfile)

        fun onUserProfileUpdateSuccessfully()
    }
}