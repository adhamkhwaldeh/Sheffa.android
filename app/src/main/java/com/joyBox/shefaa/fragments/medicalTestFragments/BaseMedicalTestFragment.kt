package com.joyBox.shefaa.fragments.medicalTestFragments

import android.support.v4.app.Fragment
import com.joyBox.shefaa.entities.User

abstract class BaseMedicalTestFragment : Fragment() {

    var titleRes: Int = 0
    lateinit var user: User

}