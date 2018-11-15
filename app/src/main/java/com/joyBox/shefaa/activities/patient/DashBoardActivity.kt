package com.joyBox.shefaa.activities.patient

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.firebase.iid.FirebaseInstanceId
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.di.component.DaggerDashBoardComponent
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.ui.NotificationContract
import com.joyBox.shefaa.di.ui.NotificationPresenter
import com.joyBox.shefaa.di.ui.RegistrationContract
import com.joyBox.shefaa.di.ui.RegistrationPresenter
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import javax.inject.Inject

class DashBoardActivity : BaseActivity(), RegistrationContract.View, NotificationContract.View,
        NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawerLayout)
    lateinit var mDrawerLayout: DrawerLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.navigationView)
    lateinit var navigationView: NavigationView

    @Inject
    lateinit var registrationPresenter: RegistrationPresenter

    @Inject
    lateinit var notificationPresenter: NotificationPresenter

    fun initDI() {
        val component = DaggerDashBoardComponent.builder()
                .registrationModule(RegistrationModule(this))
                .notificationModule(NotificationModule(this))
                .build()
        component.inject(this)
        registrationPresenter.attachView(this)
        registrationPresenter.subscribe()

        notificationPresenter.attachView(this)
        notificationPresenter.subscribe()
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    private fun initDrawer() {
        val mDrawerToggle = ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name)
        mDrawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard_layout)
        ButterKnife.bind(this)

        initToolBar()
        initDrawer()
        initDI()
        navigationView.setNavigationItemSelectedListener(this)
    }

    private fun logout() {
        registrationPresenter.logout(NetworkingHelper.LogoutUrl)
        notificationPresenter.flushToken(NetworkingHelper.FlushNotificationsUrl + FirebaseInstanceId.getInstance().token!!)

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.ManageAccount -> {
                IntentHelper.startProfileActivity(this)
            }

            R.id.MyMedicalProfile -> {
                IntentHelper.startMyMedicalProfileActivity(this)
            }

            R.id.AdvanceSearch -> {
                IntentHelper.startAdvanceSearchActivity(this)
            }
            R.id.ManageAppointments -> {
                IntentHelper.startAppointmentListPatientActivity(this)
            }
            R.id.SelfMonitor -> {
                IntentHelper.startSelfMonitorActivity(this)
            }
            R.id.Guardianship -> {
                IntentHelper.startGuardianshipActivity(this)
            }
            R.id.Magazine -> {
                IntentHelper.startMagazinesActivity(this)
            }
            R.id.Messages -> {
                IntentHelper.startMessagesActivity(this)
            }
            R.id.Notifications -> {
                IntentHelper.startNotificationsActivity(this)
            }
            R.id.Logout -> {
                logout()
                Handler().postDelayed({
                    UserRepository(context = baseContext).flushClient()
                    IntentHelper.startSignInActivity(this)
                    finish()
                }, 200)

            }
        }

        mDrawerLayout.closeDrawers()
        return false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
                    mDrawerLayout.closeDrawer(GravityCompat.START)
                else
                    mDrawerLayout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    @OnClick(R.id.ManageAccountContainer)
    fun onManageAccountClick(view: View) {
        IntentHelper.startProfileActivity(this)
    }

    @OnClick(R.id.MyMedicalProfileContainer)
    fun onMyMedicalProfileClick(view: View) {
        IntentHelper.startMyMedicalProfileActivity(this)
    }

    @OnClick(R.id.AdvanceSearchContainer)
    fun onAdvanceSearchClick(view: View) {
        IntentHelper.startAdvanceSearchActivity(this)
    }

    @OnClick(R.id.AppointmentContainer)
    fun onAppointmentClick(view: View) {
        IntentHelper.startAppointmentListPatientActivity(this)
    }

    @OnClick(R.id.SelfMonitorContainer)
    fun onSelfMonitorClick(view: View) {
        IntentHelper.startSelfMonitorActivity(this)
    }

    @OnClick(R.id.GuardianshipContainer)
    fun onGuardianshipClick(view: View) {
        IntentHelper.startGuardianshipActivity(this)
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {

    }
    /*Presenter ended*/

}