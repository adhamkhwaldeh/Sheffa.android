package com.joyBox.shefaa.entities.models

import com.google.gson.annotations.SerializedName
import java.lang.reflect.Field

class SignUpPostModel {

    var name: String = ""
    var pass: String = ""
    var mail: String = ""
    var profile_main: ProfileModel = ProfileModel()

    var legal_accept: LegalAccept = LegalAccept()

    constructor(name: String, pass: String, mail: String,
                firstName: String, lastName: String, gender: String, inviteEmail: String) {
        this.name = name
        this.pass = pass
        this.mail = mail
        this.profile_main = ProfileModel(firstName, lastName, gender, inviteEmail)
    }


    class LegalAccept {
        @SerializedName("#default_value")
        var default_value: String = "1"
    }

    class ProfileModel {

        var field_first_name: FieldUnit = FieldUnit()
        var field_last_name: FieldUnit = FieldUnit()
        var field_gender: FieldGender = FieldGender()
        var field_invitee_email: EmailUnit = EmailUnit()

        constructor()

        constructor(field_first_name: String, field_last_name: String,
                    field_gender: String, field_invitee_email: String) {
            this.field_first_name.und.value.value = field_first_name
            this.field_last_name.und.value.value = field_last_name
            this.field_gender.und = field_gender
            this.field_invitee_email.und.value.email = field_invitee_email
        }

        class FieldUnit {
            var und: FieldUnd = FieldUnd()

            class FieldUnd {
                @SerializedName("0")
                var value: SubField = SubField()

                class SubField {
                    var value: String = ""
                }
            }

        }

        class FieldGender {
            var und: String = "0"
        }

        class EmailUnit {
            var und: FieldUnd = FieldUnd()

            class FieldUnd {
                @SerializedName("0")
                var value: SubField = SubField()

                class SubField {
                    var email: String = ""
                }
            }
        }


    }

}





