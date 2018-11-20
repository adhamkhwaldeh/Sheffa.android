package com.joyBox.shefaa.activities.doctor

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.DoctorMyPatientsViewPagerAdapter

class DoctorMyPatientsActivity : BaseActivity() {


    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager

    private fun initToolbar() {
        toolbar.setTitle(R.string.MyPatients)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initViewPager() {
        viewPager.adapter = DoctorMyPatientsViewPagerAdapter(this,supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.doctor_my_patients_layout)
        ButterKnife.bind(this)
        initToolbar()
        initViewPager()
    }

}