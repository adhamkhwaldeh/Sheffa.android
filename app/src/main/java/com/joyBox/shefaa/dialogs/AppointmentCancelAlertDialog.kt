package com.joyBox.shefaa.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.listeners.OnAppointmentCancelListener

/**
 * Created by Adhamkh on 2018-11-10.
 */
class AppointmentCancelAlertDialog : DialogFragment() {

    companion object {
        const val AppointmentCancelAlertDialog_Tag="AppointmentCancelAlertDialog_Tag"
    }

    lateinit var onAppointmentCancelListener: OnAppointmentCancelListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val mView = inflater.inflate(R.layout.appointment_cancel_dailog_layout, container)
        ButterKnife.bind(this, mView)
        return mView
    }

    @OnClick(R.id.btnOk)
    fun onOkButtonClick(view: View) {
        onAppointmentCancelListener.onSubmit()
    }

    @OnClick(R.id.cancelBtn)
    fun onCancelButtonClick(view: View) {
        onAppointmentCancelListener.onCancelAction()
    }

}