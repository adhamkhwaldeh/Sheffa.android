package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.filtrations.PharmacyFilter


class PharmacyGeneralSearchViewHolder : RecyclerView.ViewHolder {

    @BindView(R.id.medicineName)
    lateinit var medicineName: EditText

    @BindView(R.id.pharmacyName)
    lateinit var pharmacyName: EditText

    @BindView(R.id.cityTextView)
    lateinit var cityTextView: TextView

    var context: Context

    constructor(itemView: View) : super(itemView) {
        ButterKnife.bind(this, itemView)
        context = itemView.context
    }

    fun getPharmacyFilter(): PharmacyFilter {
        return PharmacyFilter(query = pharmacyName.text.toString(), city = cityTextView.text.toString(),
                medicineName = medicineName.text.toString())
    }

}