package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerForgotPasswordComponent
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.ui.RegistrationContract
import com.joyBox.shefaa.di.ui.RegistrationPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.viewModels.ForgotPasswordViewHolder
import javax.inject.Inject

class ForgotPasswordActivity : BaseActivity(), RegistrationContract.View {

    @Inject
    lateinit var presenter: RegistrationPresenter

    var progressDialog: ProgressDialog = ProgressDialog()

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    lateinit var forgotPasswordViewHolder: ForgotPasswordViewHolder

    fun initToolBar() {
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initDI() {
        val component = DaggerForgotPasswordComponent.builder()
                .registrationModule(RegistrationModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgot_password_layout)
        ButterKnife.bind(this)
        forgotPasswordViewHolder = ForgotPasswordViewHolder(findViewById(android.R.id.content))

        initToolBar()
        initDI()

    }

    @OnClick(R.id.forgotPasswordBtn)
    fun OnForgotPasswordBButtonClick(view: View) {
        if (forgotPasswordViewHolder.isValid())
            presenter.forgotPassword(forgotPasswordViewHolder.getForgotModel().email)
    }

    /*presenter started*/
    override fun showProgress(show: Boolean) {
        if (show)
            progressDialog.show(supportFragmentManager, ProgressDialog.ProgressDialog_Tag)
        else
            progressDialog.dismiss()
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        super.showLoadErrorMessage(visible)
        Toast.makeText(baseContext, resources.getString(R.string.ErrorConnection), Toast.LENGTH_LONG).show();
    }

    override fun forgotPasswordFail() {
        super.forgotPasswordFail()
        Toast.makeText(baseContext, resources.getString(R.string.EmailNoFound), Toast.LENGTH_LONG).show();
        Log.v("", "")
    }

    override fun forgotPasswordSuccessfully() {
        super.forgotPasswordSuccessfully()
        Toast.makeText(baseContext, resources.getString(R.string.PleaseCheckYourEmail), Toast.LENGTH_LONG).show();
        finish()
        Log.v("", "")
    }
    /*presenter ended*/

}