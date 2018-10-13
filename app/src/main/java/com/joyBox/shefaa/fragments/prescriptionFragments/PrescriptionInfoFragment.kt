package com.joyBox.shefaa.fragments.prescriptionFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Prescription
import com.joyBox.shefaa.viewModels.PrescriptionInfoViewHolder

class PrescriptionInfoFragment : BasePrescriptionFragment() {

    companion object {

        fun getNewInstance(prescription: Prescription): PrescriptionInfoFragment {
            val f = PrescriptionInfoFragment()
            f.titleRes = R.string.PrescriptionInfo
            f.prescription = prescription
            return f
        }

    }

    lateinit var prescriptionInfoViewHolder: PrescriptionInfoViewHolder

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.prescription_info_layout, container, false)
        ButterKnife.bind(this, view)
        prescriptionInfoViewHolder = PrescriptionInfoViewHolder(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prescriptionInfoViewHolder.bind(prescription)
    }


}