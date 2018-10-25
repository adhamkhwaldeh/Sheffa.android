package com.joyBox.shefaa.dialogs

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by Adhamkh on 2018-10-25.
 */
class DiagnosiseDialog : DialogFragment() {
    companion object {
        fun newInstance(): DiagnosiseDialog {
            val diagnosiseDialog = DiagnosiseDialog()

            return diagnosiseDialog
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}