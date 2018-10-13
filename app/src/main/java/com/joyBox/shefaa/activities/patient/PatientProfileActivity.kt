package com.joyBox.shefaa.activities.patient

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.di.component.DaggerPatientProfileComponent
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.ui.RegistrationContract
import com.joyBox.shefaa.di.ui.RegistrationPresenter
import com.joyBox.shefaa.entities.MainProfile
import com.joyBox.shefaa.enums.Gender
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.enums.ProfileType
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.repositories.UserRepositoy
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class PatientProfileActivity : BaseActivity(), RegistrationContract.View {

    @Inject
    lateinit var presenter: RegistrationPresenter

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.firstName)
    lateinit var firstName: EditText

    @BindView(R.id.lastName)
    lateinit var lastName: EditText

    @BindView(R.id.email)
    lateinit var email: EditText

    @BindView(R.id.male_radioButton)
    lateinit var male: RadioButton

    @BindView(R.id.female_radioButton)
    lateinit var female: RadioButton


    fun initToolBar() {
        toolbar.setTitle(R.string.Profile)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    private fun initID() {
        val component = DaggerPatientProfileComponent.builder()
                .registrationModule(RegistrationModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        val user = UserRepositoy(this).getClient()!!.user
        presenter.loadMainProfile(userId = user.uid)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)
        ButterKnife.bind(this)
        initToolBar()
        initID()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                val user = UserRepositoy(this@PatientProfileActivity).getClient()!!.user
                presenter.loadMainProfile(userId = user.uid)
            }

            override fun onRequestPermission() {

            }
        })

    }

    private fun bindMainProfile(mainProfile: MainProfile) {
        firstName.setText(mainProfile.field_first_name)
        lastName.setText(mainProfile.field_last_name)
        email.setText(mainProfile.newest_email)
        if (mainProfile.field_gender.equals(Gender.male.gend)) {
            male.isChecked = true
        } else {
            female.isChecked = true
        }
    }

    private fun getMainProfileFromUI(): MainProfile {
        val mainProfile = MainProfile()
        val user = UserRepositoy(this).getClient()!!.user

        mainProfile.pid = user.uid
        mainProfile.field_first_name = firstName.text.toString()
        mainProfile.field_last_name = lastName.text.toString()
        mainProfile.newest_email = email.text.toString()
        if (male.isChecked) {
            mainProfile.field_gender = Gender.male.gend
        } else {
            mainProfile.field_gender = Gender.female.gend
        }

        return mainProfile
    }

    @OnClick(R.id.emailChangeBtn)
    fun onEmailChangeButtonClick(view: View) {
        IntentHelper.startChangeEmailActivity(this@PatientProfileActivity)
    }

    @OnClick(R.id.changePasswordBtn)
    fun onChangePasswordButtonClick(view: View) {
        IntentHelper.startChangePasswordActivity(this@PatientProfileActivity)
    }

    @OnClick(R.id.applybtn)
    fun onProfileApplyClick(view: View) {
        val mainProfile = getMainProfileFromUI()
        presenter.updateMainProfile(uid = mainProfile.pid, profileType = ProfileType.MAIN,
                first_name = mainProfile.field_first_name, last_name = mainProfile.field_last_name,
                gender = mainProfile.field_gender)
    }


    /*Presenter started*/
    override fun showProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showEmptyView(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun mainProfileFail() {
        stateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
    }

    override fun mainProfileSuccessfully(mainProfile: MainProfile) {
        super.mainProfileSuccessfully(mainProfile)
        bindMainProfile(mainProfile = mainProfile)
        Log.v("", "")
    }

    override fun mainProfileUpdateSuccessfully() {
        Toast.makeText(baseContext, R.string.profileUpdateSuccessfully, Toast.LENGTH_LONG).show()
    }

    override fun mainProfileUpdateFail() {
        Toast.makeText(baseContext, R.string.updateProfileFailed, Toast.LENGTH_LONG).show()
    }
    /*Presenter ended*/


}