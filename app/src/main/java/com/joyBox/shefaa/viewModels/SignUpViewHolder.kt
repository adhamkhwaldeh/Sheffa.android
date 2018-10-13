package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.models.SignUpModel
import com.joyBox.shefaa.entities.models.SignUpPostModel
import com.joyBox.shefaa.enums.Gender
import com.joyBox.shefaa.helpers.ValidationHelper
import com.joyBox.shefaa.helpers.mainHelper

//import com.brainsocket.globalpages.R
//import com.brainsocket.globalpages.data.entities.User
//import com.brainsocket.globalpages.data.entitiesModel.DuplicateModel
//import com.brainsocket.globalpages.data.entitiesModel.SignUpModel
//import com.brainsocket.globalpages.data.validations.ValidationHelper
//import com.brainsocket.globalpages.enums.UserGender
//import com.brainsocket.globalpages.utilities.mainHelper

/**
 * Created by Adhamkh on 2018-06-15.
 */
class SignUpViewHolder : RecyclerView.ViewHolder {
    var context: Context

    constructor(view: View) : super(view) {
        context = view.context
        ButterKnife.bind(this, view)
    }


    @BindView(R.id.input_username)
    lateinit var userName: EditText

    @BindView(R.id.input_password)
    lateinit var password: EditText

    @BindView(R.id.input_password_confirm)
    lateinit var passwordConfirm: EditText

    @BindView(R.id.input_email)
    lateinit var email: EditText


    @BindView(R.id.input_firstname)
    lateinit var firstname: EditText

    @BindView(R.id.input_lastname)
    lateinit var lastname: EditText


    @BindView(R.id.male_radioButton)
    lateinit var male_radioButton: RadioButton

    @BindView(R.id.female_radioButton)
    lateinit var female_radioButton: RadioButton

    @BindView(R.id.input_InviteFriendEmail)
    lateinit var inviteFriendEmail: EditText

    @BindView(R.id.privacy_checkbox)
    lateinit var acceptTerms: CheckBox


    fun isValid(): Boolean {
        var signUpModel = getSignUpModel()

        if (ValidationHelper.isNullorEmpty(signUpModel.userName)) {
            userName.error = context.resources.getString(R.string.pleaseEnterUserName)
            userName.requestFocus()
            return false
        }

        if (ValidationHelper.isNullorEmpty(signUpModel.password)) {
            password.error = context.resources.getString(R.string.pleaseenterepassword)
            password.requestFocus()
            return false
        }

        if (!signUpModel.password.equals(signUpModel.passwordConfirm)) {
            passwordConfirm.error = context.resources.getString(R.string.NoMatchPassword)
            passwordConfirm.requestFocus()
            return false
        }

        if (ValidationHelper.isEmpty(signUpModel.email)) {
            email.error = context.resources.getString(R.string.pleaseenteremail)
            email.requestFocus()
            return false
        }
        if (!ValidationHelper.isEmail(signUpModel.email)) {
            email.error = context.resources.getString(R.string.entercorrectemail)
            email.requestFocus()
            return false
        }


        if (ValidationHelper.isNullorEmpty(signUpModel.firstName)) {
            firstname.error = context.resources.getString(R.string.pleaseenterFirstName)
            firstname.requestFocus()
            return false
        }

        if (ValidationHelper.isNullorEmpty(signUpModel.lastName)) {
            lastname.error = context.resources.getString(R.string.pleaseenterLastName)
            lastname.requestFocus()
            return false
        }
        if (signUpModel.gender == null) {
            mainHelper.hideKeyboard(itemView)
            Toast.makeText(context, R.string.PleaseSelectGender, Toast.LENGTH_LONG).show()
            return false
        }

        if (!signUpModel.acceptPrivacy) {
            mainHelper.hideKeyboard(itemView)
            Toast.makeText(context, R.string.accepttermsandconditions, Toast.LENGTH_LONG).show()
            return false
        }

        return true
    }

    fun getSignUpModel(): SignUpModel {
        var signUpModel = SignUpModel()
        signUpModel.userName = userName.text.toString()
        signUpModel.password = password.text.toString()
        signUpModel.passwordConfirm = passwordConfirm.text.toString()

        signUpModel.email = email.text.toString()

        signUpModel.firstName = firstname.text.toString()
        signUpModel.lastName = lastname.text.toString()
        if (female_radioButton.isChecked)
            signUpModel.gender = Gender.female
        else if (male_radioButton.isChecked)
            signUpModel.gender = Gender.male

        signUpModel.inviteFriend = inviteFriendEmail.text.toString()
        signUpModel.acceptPrivacy = acceptTerms.isChecked
        return signUpModel
    }

    fun getSignUpPostModel(): SignUpPostModel {
        var signUpModel = getSignUpModel();
        return SignUpPostModel(name = signUpModel.userName, pass = signUpModel.password, firstName = signUpModel.firstName,
                lastName = signUpModel.lastName, gender = signUpModel.gender!!.gend, inviteEmail = signUpModel.inviteFriend,
                mail = signUpModel.email)
    }

//    fun checkDuplicate(duplicateModel: DuplicateModel) {
//        if (duplicateModel.duplicateEmail) {
//            email.error = (context.resources.getString(R.string.AlreadyExisted))
//            email.requestFocus()
//        }
//        if (duplicateModel.duplicateUserName) {
//            userName.error = (context.resources.getString(R.string.AlreadyExisted))
//            userName.requestFocus()
//        }
//    }


}