package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.CityModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.dialogs.CitiesDialog
import dagger.Component

@PerActivity
@Component(modules = [CityModule::class])
interface CityComponent {
    fun inject(citiesDialog: CitiesDialog)
}