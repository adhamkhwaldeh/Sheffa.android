package com.joyBox.shefaa.entities.models

import com.joyBox.shefaa.enums.Gender

class SignUpModel {
    var userName: String = ""
    var password: String = ""
    var passwordConfirm: String = ""
    var email: String = ""
    var firstName: String = ""
    var lastName: String = ""
    var gender: Gender? = null
    var inviteFriend = ""
    var acceptPrivacy: Boolean = false
}