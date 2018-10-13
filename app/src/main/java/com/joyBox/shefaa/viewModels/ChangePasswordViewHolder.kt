package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.models.ChangePasswordModel
import com.joyBox.shefaa.helpers.ValidationHelper

class ChangePasswordViewHolder : RecyclerView.ViewHolder {

    @BindView(R.id.input_password)
    lateinit var passwordEditText: EditText

    @BindView(R.id.input_password_confirm)
    lateinit var confirmPasswordEditText: EditText

    var context: Context

    constructor(view: View) : super(view) {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun getChangePasswordModel(): ChangePasswordModel =
            ChangePasswordModel(passwordEditText.text.toString()
                    , confirmPasswordEditText.text.toString())


    fun isValid(): Boolean {
        var changePasswordModel = getChangePasswordModel()

        if (ValidationHelper.isNullorEmpty(changePasswordModel.password)) {
            passwordEditText.error = context.resources.getString(R.string.pleaseenterepassword)
            passwordEditText.requestFocus()
            return false
        }

        if (!changePasswordModel.password.equals(changePasswordModel.confirmPassword)) {
            confirmPasswordEditText.error = context.resources.getString(R.string.NoMatchPassword)
            confirmPasswordEditText.requestFocus()
            return false
        }

        return true
    }

}