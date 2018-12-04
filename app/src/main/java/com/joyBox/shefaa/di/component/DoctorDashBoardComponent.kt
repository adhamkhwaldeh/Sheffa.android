package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.doctor.DoctorDashBoardActivity
import com.joyBox.shefaa.di.module.MessageModule
import com.joyBox.shefaa.di.module.NotificationModule
import com.joyBox.shefaa.di.module.RegistrationModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [RegistrationModule::class, NotificationModule::class, MessageModule::class])
interface DoctorDashBoardComponent {
    fun inject(doctorDashBoardActivity: DoctorDashBoardActivity)
}