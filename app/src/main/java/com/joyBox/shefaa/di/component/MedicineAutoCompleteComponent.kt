package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.autoComplete.MedicineAutoCompleteActivity
import com.joyBox.shefaa.di.module.MedicineModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [MedicineModule::class])
interface MedicineAutoCompleteComponent {
    fun inject(medicineAutoCompleteActivity: MedicineAutoCompleteActivity)
}