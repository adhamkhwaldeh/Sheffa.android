package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.Pharmacy
import com.joyBox.shefaa.views.SimpleRatingBar


class PharmacyViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

    var context: Context

    init {
        ButterKnife.bind(this, view)
        context = view.context

    }

    @BindView(R.id.pharmacyName)
    lateinit var pharmacyName: TextView

    @BindView(R.id.pharmacyMedicine)
    lateinit var pharmacyMedicine: TextView

    @BindView(R.id.pharmacyAddress)
    lateinit var pharmacyAddress: TextView

    @BindView(R.id.ratingBar)
    lateinit var simpleRatingBar: SimpleRatingBar

    fun bind(pharmacy: Pharmacy) {
        pharmacyName.text = (pharmacy.getName())
        pharmacyMedicine.text = (pharmacy.medicine_name)
        pharmacyAddress.text = (pharmacy.fullAddress)
        simpleRatingBar.rating = (pharmacy.rating)

    }

}