package com.joyBox.shefaa.activities.patient

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.entities.TestResultEntity
import com.joyBox.shefaa.helpers.IntentHelper

class TestsResultDetailsActivity : BaseActivity() {

    companion object {
        const val TestsResultDetailsActivity_Tag = "TestsResultDetailsActivity_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.testName)
    lateinit var testName: TextView

    lateinit var testResultEntity: TestResultEntity

    fun initToolBar() {
        toolbar.setTitle(R.string.tests)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tests_result_details_layout)
        ButterKnife.bind(this)

        val jSon = intent.getStringExtra(TestsResultDetailsActivity_Tag)
        testResultEntity = Gson().fromJson(jSon, TestResultEntity::class.java)

        initToolBar()

        testName.text = (testResultEntity.test_name)


    }

    @OnClick(R.id.downLoadFileBtn)
    fun onDownLoadFileClick(view: View) {
        IntentHelper.startWebsiteLink(baseContext, testResultEntity.results)
    }

}