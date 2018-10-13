package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.PresciptionListModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.medicalTestFragments.PrescriptionsFragment
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-20.
 */
@PerActivity
@Component(modules = [PresciptionListModule::class])
public interface PresciptionListComponent {
    fun inject(prescriptionsFragment: PrescriptionsFragment)
}