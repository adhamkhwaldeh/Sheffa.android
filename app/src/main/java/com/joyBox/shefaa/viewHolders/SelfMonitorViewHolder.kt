package com.joyBox.shefaa.viewHolders

import android.content.Context
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.SelfMonitorEntity

/**
 * Created by Adhamkh on 2018-10-05.
 */
class SelfMonitorViewHolder : RecyclerView.ViewHolder {

    @BindView(R.id.selfMonitorValue)
    lateinit var selfMonitorValue: TextView

    @BindView(R.id.selfMonitorTime)
    lateinit var selfMonitorTime: TextView

    @BindView(R.id.selfMonitorUnit)
    lateinit var selfMonitorUnit: TextView

    var context: Context

    constructor(view: View) : super(view) {
        ButterKnife.bind(this, view)
        context = view.context
    }

    fun bind(selfMonitorEntity: SelfMonitorEntity) {
        selfMonitorUnit.text = selfMonitorEntity.unit
        selfMonitorValue.text = selfMonitorEntity.value_of_indicator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            selfMonitorTime.text = (Html.fromHtml(selfMonitorEntity.measurement_time, Html.FROM_HTML_MODE_COMPACT))
        } else {
            selfMonitorTime.text = (Html.fromHtml(selfMonitorEntity.measurement_time))
        }
    }

}