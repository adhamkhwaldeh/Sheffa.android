package com.joyBox.shefaa.activities.doctor

import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.firebase.iid.FirebaseInstanceId
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.configurations.GlideApp
import com.joyBox.shefaa.di.component.DaggerDoctorDashBoardComponent
import com.joyBox.shefaa.di.component.DaggerPatientDashBoardComponent
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.ui.*
import com.joyBox.shefaa.entities.AutoCompleteUser
import com.joyBox.shefaa.entities.MessageEntity
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import javax.inject.Inject

class DoctorDashBoardActivity : BaseActivity(), RegistrationContract.View, NotificationContract.View, MessagesContract.View,
        NavigationView.OnNavigationItemSelectedListener {


    @Inject
    lateinit var messagesPresenter: MessagesPresenter

    @BindView(R.id.drawerLayout)
    lateinit var mDrawerLayout: DrawerLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.navigationView)
    lateinit var navigationView: NavigationView

    //    @BindView(R.id.userName)
    lateinit var userName: TextView

    //    @BindView(R.id.userEmail)
    lateinit var userEmail: TextView

    lateinit var userProfileImage: ImageView

    @Inject
    lateinit var registrationPresenter: RegistrationPresenter

    @Inject
    lateinit var notificationPresenter: NotificationPresenter

    private fun getUrl(): String {
        val client = UserRepository(this).getClient()!!
        return NetworkingHelper.MessageUnReaded + "?sess_name=" + client.sessionName +
                "&sess_id=" + client.sessid + "&token=" + client.token
    }

    fun initDI() {
        val component = DaggerDoctorDashBoardComponent.builder()
                .registrationModule(RegistrationModule(this))
                .notificationModule(NotificationModule(this))
                .messageModule(MessageModule(this))
                .build()
        component.inject(this)
        registrationPresenter.attachView(this)
        registrationPresenter.subscribe()

        notificationPresenter.attachView(this)
        notificationPresenter.subscribe()

        messagesPresenter.attachView(this)
        messagesPresenter.subscribe()
        messagesPresenter.loadUnreadMessage(getUrl())
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

    private fun initUser() {
        val user = UserRepository(this).getClient()

        userName = navigationView.getHeaderView(0).findViewById(R.id.userName)
        userEmail = navigationView.getHeaderView(0).findViewById(R.id.userEmail)
        userProfileImage = navigationView.getHeaderView(0).findViewById(R.id.userProfileImage)

        userName.text = user?.user?.name
        userEmail.text = user?.user?.mail

        val url = user?.user?.picture?.url//pictureUrl

        try {
            GlideApp.with(applicationContext).load(url)/*.resize(240, 170).centerCrop().fit()*/
                    .placeholder(R.drawable.userprofile).error(R.drawable.userprofile).into(userProfileImage
//            , object : Callback() {
//                fun onSuccess() {
//                    Log.v("Success", "Load Image")
//                }
//
//                fun onError() {
//                    Log.v("Fail", "Load Image")
//                }
//            }
            )
        } catch (ex: Exception) {

        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_dashboard_layout)
        ButterKnife.bind(this)


        initToolBar()
        initDrawer()
        initDI()
        initUser()
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
                IntentHelper.startMyMedicalProfileActivity(this, UserRepository(this@DoctorDashBoardActivity).getClient()!!.user)
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.dashboard_doctor_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.home -> {
                IntentHelper.startMainDashBoardActivity(this@DoctorDashBoardActivity)
            }
            android.R.id.home -> {
                if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
                    mDrawerLayout.closeDrawer(GravityCompat.START)
                else
                    mDrawerLayout.openDrawer(GravityCompat.START)
                return true
            }
            R.id.notifications -> {
                IntentHelper.startNotificationsActivity(this@DoctorDashBoardActivity)
            }
            R.id.messages -> {
                IntentHelper.startMessagesActivity(this@DoctorDashBoardActivity)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {

    }

    override fun onMessagesLoaded(messageList: MutableList<MessageEntity>) {
        super.onMessagesLoaded(messageList)
    }

    override fun onUnreadMessagesLoaded(count: String) {
        val messages = MenuItemCompat.getActionView(navigationView.menu.findItem(R.id.Messages)) as TextView
        messages.gravity = (Gravity.CENTER_VERTICAL)
        messages.setTypeface(null, Typeface.BOLD)
        messages.setTextColor(resources.getColor(R.color.redcolor))
        messages.text = count
        Log.v("", "")
    }

    override fun onAutoCompleteUsersLoaded(messageList: MutableList<AutoCompleteUser>) {
        super.onAutoCompleteUsersLoaded(messageList)
    }

/*Presenter ended*/


    @OnClick(R.id.MyClinicContainer)
    fun onMyClinicContainerClick(view: View) {
        IntentHelper.startDoctorProfileActivity(this@DoctorDashBoardActivity)
    }

    @OnClick(R.id.ManageAppointmentsContainer)
    fun onManageAppointmentsContainer(view: View) {
        IntentHelper.startDoctorAppointmentActivity(this@DoctorDashBoardActivity)
    }

    @OnClick(R.id.MyPatientsContainer)
    fun onMyPatientsContainerClick(view: View) {
        IntentHelper.startDoctorMyPatientsActivity(this@DoctorDashBoardActivity)
    }

    @OnClick(R.id.BudgetContainer)
    fun onBudgetContainerClick(view: View) {
        IntentHelper.startDoctorBudgetActivity(this@DoctorDashBoardActivity)
    }

    @OnClick(R.id.TherapeuticDayContainer)
    fun onTherapeuticDayContainerClick(view: View) {
        IntentHelper.startDoctorTherapeuticDayActivity(this@DoctorDashBoardActivity)
    }

    @OnClick(R.id.homeIcon)
    fun onHomeIconClick(view: View) {
        IntentHelper.startMainDashBoardActivity(this@DoctorDashBoardActivity)
    }

}