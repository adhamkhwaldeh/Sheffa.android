package com.joyBox.shefaa.enums

/**
 * Created by Adhamkh on 2018-09-25.
 */
enum class AppointmentStatus(var status: String) {
    Urgent("1"), NotUrgent("0"),
    UrgentYes("yes"), NotUrgentNo("no")
}