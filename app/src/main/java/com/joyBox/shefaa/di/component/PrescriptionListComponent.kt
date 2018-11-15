package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.PresciptionListModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.medicalTestFragments.PrescriptionsFragment
import dagger.Component

@PerActivity
@Component(modules = [PresciptionListModule::class])
interface PrescriptionListComponent {
    fun inject(prescriptionsFragment: PrescriptionsFragment)
}