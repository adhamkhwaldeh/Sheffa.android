package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.repositories.UserRepositoy

/**
 * Created by Adhamkh on 2018-08-18.
 */
class ProfileActivity : BaseActivity() {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.input_firstname)
    lateinit var firstname: EditText

    @BindView(R.id.input_lastname)
    lateinit var lastname: EditText

    @BindView(R.id.input_email)
    lateinit var email: EditText

    @BindView(R.id.male_radioButton)
    lateinit var male: RadioButton

    @BindView(R.id.female_radioButton)
    lateinit var female: RadioButton


    fun initToolBar() {
        toolbar.setTitle(R.string.Profile)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile_layout)
        ButterKnife.bind(this)
        initToolBar()

        var client = UserRepositoy(this).getClient()!!
        bindClient(client)

    }

    fun bindClient(client: Client) {
        firstname.setText(client.user.name)
        email.setText(client.user.mail)
    }

    @OnClick(R.id.applybtn)
    fun OnProfileApplyClick(view: View) {

    }


}