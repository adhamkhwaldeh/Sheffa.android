package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.MedicineAndPotionModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.prescriptionFragments.PrescriptionMedicinePotionFragment
import dagger.Component

@PerActivity
@Component(modules = [MedicineAndPotionModule::class])
 interface MedicineAndPotionComponent {
    fun inject(prescriptionMedicinePotionFragment: PrescriptionMedicinePotionFragment)
}