package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.dialogs.MedicineAndPotionDialog
import com.joyBox.shefaa.entities.MedicinePotionEntity
import com.joyBox.shefaa.viewHolders.MedicineAndPotionViewHolder

/**
 * Created by Adhamkh on 2018-08-21.
 */
class MedicineAndPotionRecyclerViewAdapter(val context: Context, private val medicineAndPotionList: List<MedicinePotionEntity>) :
        RecyclerView.Adapter<MedicineAndPotionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineAndPotionViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.medicine_and_potion_item, parent, false)
        return MedicineAndPotionViewHolder(view)
    }

    override fun getItemCount(): Int = medicineAndPotionList.size

    override fun onBindViewHolder(holder: MedicineAndPotionViewHolder, position: Int) {
        val poJo = medicineAndPotionList[position]
        holder.bind(poJo)

        holder.toggleBtn.setOnClickListener {

            val medicineAndPotionDialog: MedicineAndPotionDialog = MedicineAndPotionDialog.newInstance(poJo)
            medicineAndPotionDialog.show((context as FragmentActivity).supportFragmentManager, MedicineAndPotionDialog.MedicineAndPotionDialog_Tag)

        }

    }

}