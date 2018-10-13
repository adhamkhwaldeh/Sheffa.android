package com.joyBox.shefaa.fragments.generalSearchFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.helpers.IntentHelper

class PharmacyGeneralSearchFragment : BaseGeneralSearchFragment() {

    companion object {
        fun getNewInstance(): PharmacyGeneralSearchFragment {
            val f = PharmacyGeneralSearchFragment()
            f.titleRes = R.string.Pharmacy
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.pharmacy_general_search_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    @OnClick(R.id.searchBtn)
    fun onSearchButtonClick(view: View) {
        IntentHelper.startPharmacySearchActivity(context = context!!)
    }

}