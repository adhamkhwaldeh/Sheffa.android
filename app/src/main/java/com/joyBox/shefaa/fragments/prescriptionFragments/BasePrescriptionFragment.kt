package com.joyBox.shefaa.fragments.prescriptionFragments

import android.support.v4.app.Fragment
import com.joyBox.shefaa.entities.Prescription

/**
 * Created by Adhamkh on 2018-08-21.
 */
abstract class BasePrescriptionFragment : Fragment() {
    var titleRes: Int = 0
    lateinit var prescription: Prescription
}