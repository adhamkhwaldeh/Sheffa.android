package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.TestResultsModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.fragments.medicalTestFragments.TestResultFragment
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-21.
 */
@PerActivity
@Component(modules = [TestResultsModule::class])
public interface TestResultsComponent {
    fun inject(testResultFragment: TestResultFragment)
}