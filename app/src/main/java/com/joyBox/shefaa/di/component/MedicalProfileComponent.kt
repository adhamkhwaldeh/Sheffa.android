package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.MedicalProfileModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.medicalTestFragments.MedicalProfileFragment
import dagger.Component

@PerActivity
@Component(modules = [MedicalProfileModule::class])
interface MedicalProfileComponent {
    fun inject(medicalProfileFragment: MedicalProfileFragment)
}