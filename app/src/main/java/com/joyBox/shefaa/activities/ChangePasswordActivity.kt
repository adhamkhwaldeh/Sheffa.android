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
import com.joyBox.shefaa.di.component.DaggerChangePasswordComponent
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.ui.RegistrationContract
import com.joyBox.shefaa.di.ui.RegistrationPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.repositories.UserRepositoy
import com.joyBox.shefaa.viewModels.ChangePasswordViewHolder
import com.joyBox.shefaa.viewModels.ForgotPasswordViewHolder
import javax.inject.Inject


class ChangePasswordActivity : BaseActivity(), RegistrationContract.View {


    @Inject
    lateinit var presenter: RegistrationPresenter

    var progressDialog: ProgressDialog = ProgressDialog()

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    lateinit var changePasswordViewHolder: ChangePasswordViewHolder

    fun initToolBar() {
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initDI() {
        val component = DaggerChangePasswordComponent.builder()
                .registrationModule(RegistrationModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_password_layout)
        ButterKnife.bind(this)
        changePasswordViewHolder = ChangePasswordViewHolder(findViewById(android.R.id.content))

        initToolBar()
        initDI()
    }

    @OnClick(R.id.changePasswordBtn)
    fun onChangePasswordButtonClick(view: View) {
        if (changePasswordViewHolder.isValid()) {
            val user = UserRepositoy(this).getClient()!!.user
            val password = UserRepositoy(this).getLoginModel()!!.password
            val changePasswordModel = changePasswordViewHolder.getChangePasswordModel()
            presenter.changePassword(user.uid, password, changePasswordModel.password)
        }
    }


    /*Presenter started*/
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

    override fun changePasswordFail() {
        Toast.makeText(baseContext, resources.getString(R.string.EmailChangeFailed), Toast.LENGTH_LONG).show();
        Log.v("", "")
    }

    override fun changePasswordSuccessfully() {
        Toast.makeText(baseContext, resources.getString(R.string.EmailChangeSuccessfully), Toast.LENGTH_LONG).show();
        val loginModel = UserRepositoy(this).getLoginModel()!!
        loginModel.password = changePasswordViewHolder.getChangePasswordModel().password
        UserRepositoy(this).putLoginModel(loginModel)
        finish()
        Log.v("", "")
    }
    /*presenter ended*/

}
