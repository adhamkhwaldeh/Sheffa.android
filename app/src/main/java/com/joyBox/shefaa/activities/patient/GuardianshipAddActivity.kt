package com.joyBox.shefaa.activities.patient

import android.os.Bundle
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity

/**
 * Created by Adhamkh on 2018-10-05.
 */
class GuardianshipAddActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guardianship_add_layout)
        ButterKnife.bind(this)
    }

}