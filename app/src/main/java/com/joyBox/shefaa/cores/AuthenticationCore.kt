package com.joyBox.shefaa.cores

import com.joyBox.shefaa.entities.User

class AuthenticationCore {

    companion object {
        fun isPatientOnly(user: User): Boolean {
            val roles = user.roles
            if ((roles.iS_Doctor.isNullOrEmpty()) and (roles.iS_Laboratory.isNullOrEmpty())
                    and (roles.iS_Pharmacy.isNullOrEmpty()) and (roles.iS_support.isNullOrEmpty())
                    and (roles.is_administrator.isNullOrEmpty()))
                return true
            return false
        }

    }

}