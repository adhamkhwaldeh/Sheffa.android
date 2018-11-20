package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.joyBox.shefaa.fragments.doctorPatientsFragments.BaseDoctorPatientsFragment
import com.joyBox.shefaa.fragments.doctorPatientsFragments.DoctorMyPatientFragment
import com.joyBox.shefaa.fragments.doctorPatientsFragments.DoctorPatientsPrescriptionsFragment
import com.joyBox.shefaa.fragments.doctorPatientsFragments.DoctorPatientsTestResultFragment
import java.util.*

class DoctorMyPatientsViewPagerAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var fragmentList: MutableList<BaseDoctorPatientsFragment> = Vector()

    init {
        fragmentList.add(DoctorMyPatientFragment.getNewInstance())
        fragmentList.add(DoctorPatientsPrescriptionsFragment.getNewInstance())
        fragmentList.add(DoctorPatientsTestResultFragment.getNewInstance())
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