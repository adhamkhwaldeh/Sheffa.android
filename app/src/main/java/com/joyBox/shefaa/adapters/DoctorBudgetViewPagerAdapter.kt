package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.joyBox.shefaa.fragments.doctorBudgetFragments.BaseDoctorBudgetFragment
import com.joyBox.shefaa.fragments.doctorBudgetFragments.DoctorIncomingFragment
import com.joyBox.shefaa.fragments.doctorBudgetFragments.DoctorSpendingFragment
import com.joyBox.shefaa.fragments.doctorBudgetFragments.DoctorReportFragment
import java.util.*

class DoctorBudgetViewPagerAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var fragmentList: MutableList<BaseDoctorBudgetFragment> = Vector()

    init {
        fragmentList.add(DoctorReportFragment.getNewInstance())
        fragmentList.add(DoctorSpendingFragment.getNewInstance())
        fragmentList.add(DoctorIncomingFragment.getNewInstance())
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