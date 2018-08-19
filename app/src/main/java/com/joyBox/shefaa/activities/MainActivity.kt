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
import com.joyBox.shefaa.di.component.DaggerMessageComponent
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.ui.MessageReplayContract
import com.joyBox.shefaa.di.ui.MessagesContract
import com.joyBox.shefaa.di.ui.MessagesPresenter
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.Repository
import com.joyBox.shefaa.repositories.UserRepositoy
import javax.inject.Inject
import com.JoyBox.Shefaa.R.id.navigationView
import android.support.v4.view.MenuItemCompat
import android.view.Gravity
import android.view.View
import android.widget.TextView


/**
 * Created by Adhamkh on 2018-08-10.
 */
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, MessagesContract.View {

    @Inject
    lateinit var presenter: MessagesPresenter

    @BindView(R.id.drawerLayout)
    lateinit var mDrawerLayout: DrawerLayout

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.navigationView)
    lateinit var navigationView: NavigationView

    fun initToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setHomeButtonEnabled(true)
    }

    fun initDrawer() {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        ButterKnife.bind(this)
        initToolBar()
        initDrawer()
        initDI()

        navigationView.setNavigationItemSelectedListener(this)

    }

    fun getUrl(): String {
        var client = UserRepositoy(this).getClient()!!
        var url = NetworkingHelper.MessageUnReaded + "?sess_name=" + client.getSessionName() +
                "&sess_id=" + client.getSessid() + "&token=" + client.getToken()

        return url
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.ManageAccount -> {
                IntentHelper.startProfileActivity(this)
            }
            R.id.Messages -> {
                IntentHelper.startMessagesActivity(this)
            }
            R.id.Notifications -> {
                IntentHelper.startNotificationsActivity(this)
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
        var messages = MenuItemCompat.getActionView(navigationView.menu.findItem(R.id.Messages)) as TextView
        messages.setGravity(Gravity.CENTER_VERTICAL)
        messages.setTypeface(null, Typeface.BOLD)
        messages.setTextColor(resources.getColor(R.color.redcolor))
        messages.setText(count);
        Log.v("", "")
    }
    /*Presenter ended*/
}