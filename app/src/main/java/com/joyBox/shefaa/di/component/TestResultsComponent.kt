package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.AttachmentModule
import com.joyBox.shefaa.di.module.TestResultsModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.medicalTestFragments.TestResultFragment
import dagger.Component

@PerActivity
@Component(modules = [TestResultsModule::class])
interface TestResultsComponent {
    fun inject(testResultFragment: TestResultFragment)
}