package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.fragments.prescriptionFragments.BasePrescriptionFragment
import com.joyBox.shefaa.fragments.prescriptionFragments.PrescriptionFollowUpFragment
import com.joyBox.shefaa.fragments.prescriptionFragments.PrescriptionMedicinePotionFragment
import com.joyBox.shefaa.fragments.prescriptionFragments.PrescriptionInfoFragment
import java.util.*

class PrescriptionViewPagerAdapter(val context: Context, val prescription: Prescription,
                                   fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var fragmentList: MutableList<BasePrescriptionFragment> = Vector()

    init {
        fragmentList.add(PrescriptionInfoFragment.getNewInstance(prescription))
        fragmentList.add(PrescriptionMedicinePotionFragment.getNewInstance(prescription))
        fragmentList.add(PrescriptionFollowUpFragment.getNewInstance(prescription))
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.getString(fragmentList[position].titleRes)
    }

}
