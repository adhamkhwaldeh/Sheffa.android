package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.DoctorModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.doctorPatientsFragments.DoctorPatientsTestResultFragment
import dagger.Component

@PerActivity
@Component(modules = [DoctorModule::class])
interface DoctorPatientsTestResultComponent {
    fun inject(doctorPatientsTestResultFragment: DoctorPatientsTestResultFragment)
}
