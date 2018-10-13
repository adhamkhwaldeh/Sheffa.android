package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.AutoCompleteUser

/**
 * Created by Adhamkh on 2018-10-12.
 */
interface OnAutoCompleteUsersListener {
    fun autoCompleteUsersLoading()
    fun autoCompleteUsersNoData()
    fun autoCompleteUsersSuccessFully(autoCompleteUserList: MutableList<AutoCompleteUser>)
    fun autoCompleteUsersInternetConnection()
}