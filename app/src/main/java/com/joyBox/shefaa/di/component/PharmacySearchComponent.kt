package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.PharmacySearchActivity
import com.joyBox.shefaa.di.module.PharmacyModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [PharmacyModule::class])
interface PharmacySearchComponent {
    fun inject(PharmacySearchActivity: PharmacySearchActivity)
}