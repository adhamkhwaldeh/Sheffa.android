package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.joyBox.shefaa.fragments.medicalTestFragments.BaseMedicalTestFragment
import com.joyBox.shefaa.fragments.medicalTestFragments.MedicalProfileFragment
import com.joyBox.shefaa.fragments.medicalTestFragments.PrescriptionsFragment
import com.joyBox.shefaa.fragments.medicalTestFragments.TestResultFragment
import java.util.*

class MedicalTestsViewPagerAdapter(val context: Context, fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    var fragmentList: MutableList<BaseMedicalTestFragment> = Vector()

    init {
        fragmentList.add(MedicalProfileFragment.getNewInstance())
        fragmentList.add(TestResultFragment.getNewInstance())
        fragmentList.add(PrescriptionsFragment.getNewInstance())
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