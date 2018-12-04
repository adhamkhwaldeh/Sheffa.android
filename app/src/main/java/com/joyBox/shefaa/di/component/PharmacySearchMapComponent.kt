package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.PharmacySearchMapActivity
import com.joyBox.shefaa.di.module.PharmacyModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [PharmacyModule::class])
interface PharmacySearchMapComponent {
    fun inject(pharmacySearchMapActivity: PharmacySearchMapActivity)
}