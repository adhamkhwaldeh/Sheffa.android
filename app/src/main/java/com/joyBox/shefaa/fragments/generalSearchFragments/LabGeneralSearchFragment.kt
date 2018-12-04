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
import com.joyBox.shefaa.viewModels.LabGeneralSearchViewHolder

class LabGeneralSearchFragment : BaseGeneralSearchFragment() {

    companion object {
        fun getNewInstance(): LabGeneralSearchFragment {
            val f = LabGeneralSearchFragment()
            f.titleRes = R.string.Lab
            return f
        }
    }

    lateinit var labGeneralSearchViewHolder: LabGeneralSearchViewHolder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.lab_general_search_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        labGeneralSearchViewHolder = LabGeneralSearchViewHolder(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        RxBus.listen(MessageEvent::class.java).subscribe {
            when (it.action) {
                EventActions.CityLab_Tag -> {
                    labGeneralSearchViewHolder.cityTextView.text = it.message as String
                }
            }
        }
    }

    @OnClick(R.id.searchBtn)
    fun onSearchButtonClick(view: View) {
        IntentHelper.startLabSearchActivity(context!!, labGeneralSearchViewHolder.getLabFilter())
    }

    @OnClick(R.id.cityTextView)
    fun onCityTextViewClick(view: View) {
        val cityDialog = CitiesDialog.newInstance(CityEnum.LABORATORY)
        cityDialog.show(childFragmentManager, CitiesDialog.CitiesDialog_Tag)
    }


}