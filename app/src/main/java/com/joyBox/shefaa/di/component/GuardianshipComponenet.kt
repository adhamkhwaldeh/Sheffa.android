package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.GuardianshipActivity
import com.joyBox.shefaa.di.module.GuardianshipModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-10-05.
 */
@PerActivity
@Component(modules = [GuardianshipModule::class])
public interface GuardianshipComponenet {
    fun inject(guardianshipActivity: GuardianshipActivity)
}