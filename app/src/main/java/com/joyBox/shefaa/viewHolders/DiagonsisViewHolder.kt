package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.DiagnosiseAutoComplete

/**
 * Created by Adhamkh on 2018-12-04.
 */
class DiagonsisViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    @BindView(R.id.diagnosisName)
    lateinit var diagnosisName: TextView

    val context: Context = view.context

    init {
        ButterKnife.bind(this, view)
    }

    fun bind(diagnosiseAutoComplete: DiagnosiseAutoComplete) {
        diagnosisName.text = diagnosiseAutoComplete.name
    }

}

