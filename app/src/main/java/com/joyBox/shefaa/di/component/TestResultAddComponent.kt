package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.patient.TestResultAddActivity
import com.joyBox.shefaa.di.module.AttachmentModule
import com.joyBox.shefaa.di.module.TestResultsModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

@PerActivity
@Component(modules = [TestResultsModule::class, AttachmentModule::class])
interface TestResultAddComponent {
    fun inject(testResultAddActivity: TestResultAddActivity)
}