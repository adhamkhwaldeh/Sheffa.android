package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.ReportModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.doctorBudgetFragments.DoctorSpendingFragment
import dagger.Component


@PerActivity
@Component(modules = [ReportModule::class])
interface DoctorSpendingComponent {
    fun inject(doctorSpendingFragment: DoctorSpendingFragment)
}
