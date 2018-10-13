package com.joyBox.shefaa.adapters

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.dialogs.FollowUpDialog
import com.joyBox.shefaa.entities.PrescriptionFollowUp
import com.joyBox.shefaa.viewHolders.PrescriptionFollowUpViewHolder

class PrescriptionFollowUpRecyclerViewAdapter(val context: Context, val prescriptionFollowUpList: List<PrescriptionFollowUp>) :
        RecyclerView.Adapter<PrescriptionFollowUpViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrescriptionFollowUpViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.medicine_and_potion_item, parent, false)
        return PrescriptionFollowUpViewHolder(view)
    }

    override fun getItemCount(): Int = prescriptionFollowUpList.size

    override fun onBindViewHolder(holder: PrescriptionFollowUpViewHolder, position: Int) {
        val poJo = prescriptionFollowUpList[position]
        holder.bind(poJo)
        holder.toggleBtn.setOnClickListener {
            val followUpDialog: FollowUpDialog = FollowUpDialog.newInstance(poJo)
            followUpDialog.show((context as FragmentActivity).supportFragmentManager,
                    FollowUpDialog.FollowUpDialog_Tag)
        }
    }

}