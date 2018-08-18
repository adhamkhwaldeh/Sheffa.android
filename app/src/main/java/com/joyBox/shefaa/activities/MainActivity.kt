package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.repositories.Repository
import com.joyBox.shefaa.repositories.UserRepositoy

/**
 * Created by Adhamkh on 2018-08-10.
 */
class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)
        ButterKnife.bind(this)
        initToolBar()
        initDrawer()

        navigationView.setNavigationItemSelectedListener(this)

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
        return true
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
}