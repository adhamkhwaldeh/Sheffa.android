package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R

class TestResultAddActivity : BaseActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    fun initToolBar() {
        toolbar.setTitle(R.string.AddTestResult)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_result_add_layout)
        ButterKnife.bind(this)
        initToolBar()

    }

}