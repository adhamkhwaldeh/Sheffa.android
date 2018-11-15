package com.joyBox.shefaa.repositories

import android.content.Context
import com.google.gson.Gson
import com.joyBox.shefaa.entities.Client
import com.joyBox.shefaa.entities.models.LoginModel

/**
 * Created by Adhamkh on 2018-08-10.
 */
class UserRepository constructor(context: Context) : Repository(context) {

    companion object {

        var Client_TAG: String = "Client_TAG"
        var Client_Default_TAG: String = ""

        var Cookei_TAG: String = "Cookei_TAG"
        var Cookei_Default_TAG: String = ""

        var LoginModel_Tag = "LoginModel_Tag"
        var LoginModel_Default_Tag = ""

    }

    /*Client Started*/
    fun putClient(client: Client) {
        editor.putString(Client_TAG, Gson().toJson(client)).apply()
        editor.commit()
    }

    fun getClient(): Client? {
        var client: Client? = null
        var pojo = pref.getString(Client_TAG, Client_Default_TAG)
        if (pojo.isNotEmpty())
            client = Gson().fromJson(pojo, Client::class.java)
        return client
    }

    fun flushClient() {
        editor.putString(Client_TAG, Client_Default_TAG)
        editor.commit()
    }

    /*Client Ended*/


    /*Client Started*/
    fun putLoginModel(loginModel: LoginModel) {
        editor.putString(LoginModel_Tag, Gson().toJson(loginModel)).apply()
        editor.commit()
    }

    fun getLoginModel(): LoginModel? {
        var loginModel: LoginModel? = null
        var pojo = pref.getString(LoginModel_Tag, LoginModel_Default_Tag)
        if (pojo.isNotEmpty())
            loginModel = Gson().fromJson(pojo, LoginModel::class.java)
        return loginModel
    }

    fun flushLoginModel() {
        editor.putString(LoginModel_Tag, LoginModel_Default_Tag)
        editor.commit()
    }

    /*Client Ended*/


    /*Client Started*/
    fun putCookei(cookei: String) {
        editor.putString(Cookei_TAG, cookei).apply()
        editor.commit()
    }

    fun getCookei(): String? {
        var cookei: String? = null
        var pojo = pref.getString(Cookei_TAG, Cookei_Default_TAG)
        if (pojo.isNotEmpty())
            cookei = pojo
        return cookei
    }

    fun flushCookei() {
        editor.putString(Cookei_TAG, Cookei_Default_TAG)
        editor.commit()
    }

    /*Client Ended*/


}