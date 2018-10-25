package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.DoctorProfile
import com.joyBox.shefaa.entities.MedicalProfile


class MedicalProfileContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadUserProfile(userId: String, profileType: String)
        fun updateUserProfile(url: String)

        fun loadDoctorProfile(userId: String, profileType: String)
        fun updateDoctorProfile(url: String)

    }

    interface View : BaseContract.View {
        fun onUserProfileLoaded(medicalProfile: MedicalProfile) {}
        fun onUserProfileUpdateSuccessfully() {}

        fun onDoctorProfileLoaded(doctorProfile: DoctorProfile) {}
        fun onDoctorProfileUpdateSuccessfully() {}

    }
}