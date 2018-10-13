package com.joyBox.shefaa.activities

import android.graphics.Typeface
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerMainComponent
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.ui.MessagesContract
import com.joyBox.shefaa.di.ui.MessagesPresenter
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepositoy
import javax.inject.Inject
import android.support.v4.view.MenuItemCompat
import android.view.Gravity
import android.view.View
import android.widget.TextView
import butterknife.OnClick
import com.joyBox.shefaa.cores.AuthenticationCore
import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.User
import org.w3c.dom.Text
import java.net.Authenticator

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, MessagesContract.View {

    @Inject
    lateinit var presenter: MessagesPresenter

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


    fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    private fun initDrawer() {
        val mDrawerToggle = ActionBarDrawerToggle(this, mDrawerLayout, R.string.app_name, R.string.app_name)
        mDrawerLayout.addDrawerListener(mDrawerToggle)
        mDrawerToggle.syncState()

    }

    private fun initDI() {
        val component = DaggerMainComponent.builder()
                .messageModule(MessageModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadUnreadMessage(getUrl())
    }

    private fun initUser() {
        val user = UserRepositoy(this).getClient()

        userName = navigationView.getHeaderView(0).findViewById(R.id.userName)
        userEmail = navigationView.getHeaderView(0).findViewById(R.id.userEmail)

        userName.text = user?.user?.name
        userEmail.text = user?.user?.mail
    }

    private fun initRedirection() {
        val user: User = UserRepositoy(this).getClient()!!.user
        if (AuthenticationCore.isPatientOnly(user)) {
            IntentHelper.startDashBoardActivity(this)
            finish()
        }
        Log.v("", "")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        ButterKnife.bind(this)
        initToolBar()
        initDrawer()
        initDI()
        initUser()

        initRedirection()

        navigationView.setNavigationItemSelectedListener(this)

    }

    @OnClick(R.id.doctorBtn)
    fun onDoctorSearchButtonClick(view: View) {
        IntentHelper.startAdvanceSearchActivity(this)
//        IntentHelper.startDoctorSearchActivity(this)
        Log.v("Clicked", view.id.toString())
    }

    private fun getUrl(): String {
        val client = UserRepositoy(this).getClient()!!
        return NetworkingHelper.MessageUnReaded + "?sess_name=" + client.sessionName +
                "&sess_id=" + client.sessid + "&token=" + client.token
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.ManageAppointments -> {
                IntentHelper.startAppointmentListPatientActivity(this)
            }
            R.id.ManageAccount -> {
                IntentHelper.startProfileActivity(this)
            }
            R.id.MyMedicalProfile -> {
                IntentHelper.startMyMedicalProfileActivity(this)
            }
            R.id.Messages -> {
                IntentHelper.startMessagesActivity(this)
            }
            R.id.Notifications -> {
                IntentHelper.startNotificationsActivity(this)
            }
            R.id.Magazine -> {
                IntentHelper.startMagazinesActivity(this)
            }
            R.id.Logout -> {
                UserRepositoy(context = baseContext).flushClient()
                IntentHelper.startSignInActivity(this)
                finish()
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


    /*Presenter started*/
    override fun showProgress(show: Boolean) {

    }

    override fun onUnreadMessagesLoaded(count: String) {
        val messages = MenuItemCompat.getActionView(navigationView.menu.findItem(R.id.Messages)) as TextView
        messages.gravity = (Gravity.CENTER_VERTICAL)
        messages.setTypeface(null, Typeface.BOLD)
        messages.setTextColor(resources.getColor(R.color.redcolor))
        messages.text = count
        Log.v("", "")
    }
    /*Presenter ended*/
}