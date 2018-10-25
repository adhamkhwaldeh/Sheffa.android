package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.joyBox.shefaa.fragments.doctorAppointmentFragments.BaseDoctorAppointmentFragment
import com.joyBox.shefaa.fragments.doctorAppointmentFragments.DoctorCalendarClinicFragment
import com.joyBox.shefaa.fragments.doctorAppointmentFragments.DoctorShiftAppointmentFragment
import com.joyBox.shefaa.fragments.doctorAppointmentFragments.DoctorTreatmentDayFragment
import java.util.*

/**
 * Created by Adhamkh on 2018-10-24.
 */
class DoctorAppointmentViewPagerAdapter(val context: Context, fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    var fragmentList: MutableList<BaseDoctorAppointmentFragment> = Vector()

    init {
        fragmentList.add(DoctorCalendarClinicFragment.getNewInstance())
        fragmentList.add(DoctorTreatmentDayFragment.getNewInstance())
        fragmentList.add(DoctorShiftAppointmentFragment.getNewInstance())
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
