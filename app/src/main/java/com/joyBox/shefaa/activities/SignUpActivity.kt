package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.CompoundButton
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnCheckedChanged
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerSignUpComponent
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.ui.RegistrationContract
import com.joyBox.shefaa.di.ui.RegistrationPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.dialogs.TermsAndConditionsDialog
import com.joyBox.shefaa.viewHolders.SignUpViewHolder
import javax.inject.Inject

/**
 * Created by Adhamkh on 2018-08-10.
 */
class SignUpActivity : BaseActivity(), RegistrationContract.View {

    @Inject
    lateinit var presenter: RegistrationPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    lateinit var signUpViewHolder: SignUpViewHolder

    private fun initToolBar() {
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initDI() {
        val component = DaggerSignUpComponent.builder()
                .registrationModule(RegistrationModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    var progressDialog: ProgressDialog = ProgressDialog()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_layout)
        ButterKnife.bind(this)
        initToolBar()
        initDI()
        signUpViewHolder = SignUpViewHolder(findViewById(android.R.id.content))
    }

    @OnClick(R.id.signupbtn)
    fun onSignUpButtonClick(view: View) {
        if (signUpViewHolder.isValid())
            presenter.signUp(signUpViewHolder.getSignUpPostModel())
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {
        if (show)
            progressDialog.show(supportFragmentManager, ProgressDialog.ProgressDialog_Tag)
        else
            progressDialog.dismiss()
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        Toast.makeText(baseContext, resources.getString(R.string.ErrorConnection), Toast.LENGTH_LONG).show();
    }

    override fun signUpFail() {
        super.signUpFail()
    }

    override fun SignUpSuccessfully() {
        super.SignUpSuccessfully()

    }
    /*presenter ended*/

    @OnCheckedChanged(R.id.privacy_checkbox)
    fun onPrivacyChechBoxClick(view: CompoundButton, isChecked: Boolean) {
        if (isChecked) {
            var termsAndConditionsDialog = TermsAndConditionsDialog.newInstance()
            termsAndConditionsDialog.show(supportFragmentManager, TermsAndConditionsDialog.TermsAndConditionsDialog_Tag)
        }
    }

}