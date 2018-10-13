package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.adapters.PrescriptionViewPagerAdapter
import com.joyBox.shefaa.entities.Prescription

class PrescriptionDetailsActivity : BaseActivity() {

    companion object {
        const val Prescription_Tag = "Prescription_Tag"
    }

    lateinit var prescription: Prescription

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager

    fun initToolBar() {
        toolbar.title = getString(R.string.PrescriptionDetail)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initViewPager() {
        viewPager.adapter = PrescriptionViewPagerAdapter(context = baseContext,
                prescription = prescription, fragmentManager = supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.prescription_details_layout)
        val json = intent.getStringExtra(Prescription_Tag)
        prescription = Gson().fromJson(json, Prescription::class.java)
        ButterKnife.bind(this)
        initToolBar()
        initViewPager()

    }

}