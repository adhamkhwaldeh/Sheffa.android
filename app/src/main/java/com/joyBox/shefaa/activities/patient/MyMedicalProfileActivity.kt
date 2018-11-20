package com.joyBox.shefaa.activities.patient

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.adapters.MedicalTestsViewPagerAdapter
import com.joyBox.shefaa.entities.User

class MyMedicalProfileActivity : BaseActivity() {

    companion object {
        const val MyMedicalProfileActivity_Tag = "MyMedicalProfileActivity_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.tabLayout)
    lateinit var tabLayout: TabLayout

    @BindView(R.id.viewPager)
    lateinit var viewPager: ViewPager

    private fun initToolbar() {
        toolbar.setTitle(R.string.MyMedicalProfile)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initViewPager() {
        val user = Gson().fromJson(intent.getStringExtra(MyMedicalProfileActivity_Tag), User::class.java)
        viewPager.adapter = MedicalTestsViewPagerAdapter(this, user, supportFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_result_layout)
        ButterKnife.bind(this)
        initToolbar()
        initViewPager()
    }


}