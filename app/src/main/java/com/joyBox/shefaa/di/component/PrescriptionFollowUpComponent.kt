package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.PrescriptionFollowUpModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.prescriptionFragments.PrescriptionFollowUpFragment
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-21.
 */

@PerActivity
@Component(modules = [PrescriptionFollowUpModule::class])
public interface PrescriptionFollowUpComponent {
    fun inject(PrescriptionFollowUpFragment: PrescriptionFollowUpFragment)
}