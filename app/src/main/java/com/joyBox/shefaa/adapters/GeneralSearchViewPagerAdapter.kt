package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.joyBox.shefaa.fragments.generalSearchFragments.BaseGeneralSearchFragment
import com.joyBox.shefaa.fragments.generalSearchFragments.DoctorGeneralSearchFragment
import com.joyBox.shefaa.fragments.generalSearchFragments.LabGeneralSearchFragment
import com.joyBox.shefaa.fragments.generalSearchFragments.PharmacyGeneralSearchFragment
import java.util.*

class GeneralSearchViewPagerAdapter(val context: Context, fragmentManager: FragmentManager)
    : FragmentPagerAdapter(fragmentManager) {

    var fragmentList: MutableList<BaseGeneralSearchFragment> = Vector()

    init {
        fragmentList.add(DoctorGeneralSearchFragment.getNewInstance())
        fragmentList.add(PharmacyGeneralSearchFragment.getNewInstance())
        fragmentList.add(LabGeneralSearchFragment.getNewInstance())
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
