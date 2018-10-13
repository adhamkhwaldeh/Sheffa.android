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
import com.joyBox.shefaa.di.component.DaggerChangeEmailComponent
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.ui.RegistrationContract
import com.joyBox.shefaa.di.ui.RegistrationPresenter
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.repositories.UserRepositoy
import com.joyBox.shefaa.viewModels.ForgotPasswordViewHolder
import javax.inject.Inject

class ChangeEmailActivity : BaseActivity(), RegistrationContract.View {


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
        val component = DaggerChangeEmailComponent.builder()
                .registrationModule(RegistrationModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.change_email_layout)
        ButterKnife.bind(this)
        forgotPasswordViewHolder = ForgotPasswordViewHolder(findViewById(android.R.id.content))

        initToolBar()
        initDI()

    }

    @OnClick(R.id.changeEmailBtn)
    fun onChangeEmailButtonClick(view: View) {
        if (forgotPasswordViewHolder.isValid()) {
            val user = UserRepositoy(this).getClient()!!.user
            val password = UserRepositoy(this).getLoginModel()!!.password
            presenter.changeEmail(user.uid, forgotPasswordViewHolder.getForgotModel().email, password)
        }

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

    override fun changeEmailFail() {
        Toast.makeText(baseContext, resources.getString(R.string.EmailChangeFailed), Toast.LENGTH_LONG).show();
        Log.v("", "")
    }

    override fun changeEmailSuccessfully() {
        Toast.makeText(baseContext, resources.getString(R.string.EmailChangeSuccessfully), Toast.LENGTH_LONG).show();
        val user = UserRepositoy(this).getClient()!!
        user.user.mail = forgotPasswordViewHolder.getForgotModel().email
        UserRepositoy(this).putClient(user)

        finish()
        Log.v("", "")
    }
    /*presenter ended*/

}
