package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.DoctorProfile
import com.joyBox.shefaa.entities.MedicalProfile
import com.joyBox.shefaa.entities.MedicinePotionEntity
import com.joyBox.shefaa.networking.listeners.*
import com.joyBox.shefaa.networking.tasks.*

class MedicalProfilePresenter constructor(val context: Context) : MedicalProfileContract.Presenter {
    private lateinit var view: MedicalProfileContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: MedicalProfileContract.View) {
        this.view = view
    }

    override fun loadUserProfile(userId: String, profileType: String) {
        MedicalProfileAsync(userId, profileType, object : OnMedicalProfileListener {
            override fun onMedicalProfileLoading() {
                view.showProgress(true)
            }

            override fun onMedicalProfileInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onMedicalProfileSuccessFully(medicalProfile: MedicalProfile) {
                view.showProgress(false)
                view.onUserProfileLoaded(medicalProfile)
            }

            override fun onMedicalProfileNoData() {
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun updateUserProfile(url: String) {
        MedicalProfileUpdateAsync(url, object : OnMedicalProfileUpdateListener {
            override fun onMedicalProfileUpdateLoading() {
                view.showProgress(true)
            }

            override fun onMedicalProfileUpdateInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onMedicalProfileUpdateSuccessFully() {
                view.showProgress(false)
                view.onUserProfileUpdateSuccessfully()
            }

            override fun onMedicalProfileUpdateNoData() {
                view.showEmptyView(true)
            }
        }).execute()

    }


    override fun loadDoctorProfile(userId: String, profileType: String) {
        DoctorProfileAsync(userId, object : OnDoctorProfileListener {
            override fun onDoctorProfileLoading() {
                view.showProgress(true)
            }

            override fun onDoctorProfileInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onDoctorProfileSuccessFully(doctorProfile: DoctorProfile) {
                view.showProgress(false)
                view.onDoctorProfileLoaded(doctorProfile)
            }

            override fun onDoctorProfileNoData() {
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun updateDoctorProfile(url: String) {
        DoctorProfileUpdateAsync(url, object : OnDoctorProfileUpdateListener {
            override fun onDoctorProfileUpdateLoading() {
                view.showProgress(true)
            }

            override fun onDoctorProfileUpdateInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onDoctorProfileUpdateSuccessFully() {
                view.showProgress(false)
                view.onDoctorProfileUpdateSuccessfully()
            }

            override fun onDoctorProfileUpdateFail() {
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun unSubscribe() {

    }
}
