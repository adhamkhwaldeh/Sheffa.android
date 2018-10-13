package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.models.ForgotPasswordModel
import com.joyBox.shefaa.helpers.ValidationHelper

/**
 * Created by Adhamkh on 2018-06-18.
 */
class ForgotPasswordViewHolder : RecyclerView.ViewHolder {

    @BindView(R.id.input_email)
    lateinit var email: EditText

    var context: Context

    constructor(view: View) : super(view) {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun isValid(): Boolean {
        var forgotModel = getForgotModel()
        if (ValidationHelper.isEmpty(forgotModel.email)) {
            email.error = context.resources.getString(R.string.pleaseenteremail)
            email.requestFocus()
            return false
        }
        if (!ValidationHelper.isEmail(forgotModel.email)) {
            email.error = context.resources.getString(R.string.entercorrectemail)
            email.requestFocus()
            return false
        }
        return true
    }

    fun getForgotModel(): ForgotPasswordModel {
        val forgotModel = ForgotPasswordModel()
        forgotModel.email = email.text.toString()
        return forgotModel
    }


}