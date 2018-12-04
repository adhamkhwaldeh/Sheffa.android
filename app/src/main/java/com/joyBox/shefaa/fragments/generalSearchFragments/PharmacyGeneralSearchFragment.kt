package com.joyBox.shefaa.fragments.generalSearchFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.dialogs.CitiesDialog
import com.joyBox.shefaa.enums.CityEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.viewModels.PharmacyGeneralSearchViewHolder

class PharmacyGeneralSearchFragment : BaseGeneralSearchFragment() {

    companion object {
        fun getNewInstance(): PharmacyGeneralSearchFragment {
            val f = PharmacyGeneralSearchFragment()
            f.titleRes = R.string.Pharmacy
            return f
        }
    }

    lateinit var pharmacyGeneralSearchViewHolder: PharmacyGeneralSearchViewHolder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.pharmacy_general_search_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        pharmacyGeneralSearchViewHolder = PharmacyGeneralSearchViewHolder(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RxBus.listen(MessageEvent::class.java).subscribe {
            when (it.action) {
                EventActions.CityPharmacy_Tag -> {
                    pharmacyGeneralSearchViewHolder.cityTextView.text = it.message as String
                }
            }
        }
    }

    @OnClick(R.id.searchBtn)
    fun onSearchButtonClick(view: View) {
        IntentHelper.startPharmacySearchActivity(context = context!!,
                pharmacyFilter = pharmacyGeneralSearchViewHolder.getPharmacyFilter())
    }

    @OnClick(R.id.cityTextView)
    fun onCityTextViewClick(view: View) {
        val cityDialog = CitiesDialog.newInstance(CityEnum.PHARMACIST)
        cityDialog.show(childFragmentManager, CitiesDialog.CitiesDialog_Tag)
    }


}