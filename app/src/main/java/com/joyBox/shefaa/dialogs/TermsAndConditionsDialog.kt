package com.joyBox.shefaa.dialogs

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerForgotPasswordComponent
import com.joyBox.shefaa.di.component.DaggerTermsAndConditionsComponent
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.module.TermsAndConditionsModule
import com.joyBox.shefaa.di.ui.TermsAndConditionsContract
import com.joyBox.shefaa.di.ui.TermsAndConditionsPresenter
import com.joyBox.shefaa.entities.TermsAndConditionsEntity
import com.joyBox.shefaa.networking.NetworkingHelper
import javax.inject.Inject
import android.text.method.ScrollingMovementMethod


/**
 * Created by Adhamkh on 2018-08-17.
 */
class TermsAndConditionsDialog : DialogFragment(), TermsAndConditionsContract.View {

    companion object {
        val TermsAndConditionsDialog_Tag = "TermsAndConditionsDialog"

        fun newInstance(): TermsAndConditionsDialog {
            val f = TermsAndConditionsDialog()
            f.isCancelable = false
            // Supply num input as an argument.
            val args = Bundle()
            f.arguments = args
            return f
        }
    }


    @Inject
    lateinit var presenter: TermsAndConditionsPresenter

    private fun initDI() {
        val component = DaggerTermsAndConditionsComponent.builder()
                .termsAndConditionsModule(TermsAndConditionsModule(this.activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadTermsAndConditions(NetworkingHelper.TermsAndConditionsUrl)
    }

    @BindView(R.id.termAndConditionsTextView)
    lateinit var termAndConditionsTextView: TextView

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = activity!!.layoutInflater
        val view = inflater.inflate(R.layout.terms_and_conditions_layout, null)
        ButterKnife.bind(this, view)
        val builder = AlertDialog.Builder(activity!!)
        builder.setView(view)
        builder.setTitle(activity!!.getString(R.string.TermsAndConditions))
                .setNeutralButton(activity!!.getString(R.string.Ok), null)
//                .setNegativeButton(activity!!.getString(R.string.cancel), null)
        return builder.create()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initDI()
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {
        if (show) {

        } else {

        }
    }

    override fun showEmptyView(visible: Boolean) {
        super.showEmptyView(visible)
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        super.showLoadErrorMessage(visible)
    }

    override fun onTermsAndConditionsLoaded(termsAndConditions: TermsAndConditionsEntity) {
        termAndConditionsTextView.movementMethod = ScrollingMovementMethod()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            termAndConditionsTextView.text = (Html.fromHtml(termsAndConditions.conditions, Html.FROM_HTML_MODE_COMPACT))
        } else {
            termAndConditionsTextView.text = (Html.fromHtml(termsAndConditions.conditions))
        }
        termAndConditionsTextView.movementMethod = ScrollingMovementMethod()
        Log.v("", "");
    }
    /*Presenter started*/

}