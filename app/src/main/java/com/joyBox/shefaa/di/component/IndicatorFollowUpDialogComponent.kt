package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.ReminderModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.dialogs.IndicatorFollowUpDialog
import dagger.Component

@PerActivity
@Component(modules = [ReminderModule::class])
interface IndicatorFollowUpDialogComponent {
    fun inject(indicatorFollowUpDialog: IndicatorFollowUpDialog)
}