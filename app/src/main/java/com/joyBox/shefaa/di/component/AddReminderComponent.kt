package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.AddReminderActivity
import com.joyBox.shefaa.di.module.ReminderModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [ReminderModule::class])
interface AddReminderComponent {
    fun inject(addReminderActivity: AddReminderActivity)
}