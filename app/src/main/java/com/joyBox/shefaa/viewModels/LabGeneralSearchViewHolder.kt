package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.filtrations.LabFilter

/**
 * Created by Adhamkh on 2018-11-10.
 */
class LabGeneralSearchViewHolder : RecyclerView.ViewHolder {

    @BindView(R.id.labName)
    lateinit var labName: EditText

    @BindView(R.id.addressEditText)
    lateinit var addressEditText: EditText

    lateinit var context: Context

    constructor(itemView: View) : super(itemView) {
        ButterKnife.bind(this, itemView)
        context = itemView.context
    }


    fun getLabFilter(): LabFilter {
        return LabFilter(query = labName.text.toString(), address = addressEditText.text.toString())
    }
}