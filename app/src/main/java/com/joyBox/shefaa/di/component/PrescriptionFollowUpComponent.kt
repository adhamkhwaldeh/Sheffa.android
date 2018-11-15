package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.PrescriptionFollowUpModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.prescriptionFragments.PrescriptionFollowUpFragment
import dagger.Component

@PerActivity
@Component(modules = [PrescriptionFollowUpModule::class])
interface PrescriptionFollowUpComponent {
    fun inject(PrescriptionFollowUpFragment: PrescriptionFollowUpFragment)
}