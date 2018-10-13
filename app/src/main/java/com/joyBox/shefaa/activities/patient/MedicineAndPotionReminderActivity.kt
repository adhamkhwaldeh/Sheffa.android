package com.joyBox.shefaa.activities.patient

import android.os.Bundle
import android.support.v7.widget.Toolbar
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity

class MedicineAndPotionReminderActivity : BaseActivity() {

    companion object {
        const val Medicine_And_Potion_Reminder_Tag = "Medicine_And_Potion_Reminder_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    fun initToolBar() {
        toolbar.setTitle(R.string.RemindSettings)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.medicine_and_potion_reminder_layout)
        ButterKnife.bind(this)

        initToolBar()

    }

}