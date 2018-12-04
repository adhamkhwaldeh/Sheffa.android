package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.TestResultsModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.dialogs.AvailableTestDialog
import dagger.Component

@PerActivity
@Component(modules = [ TestResultsModule::class])
public interface AvailableTestComponent {
    fun inject(availableTestDialog: AvailableTestDialog)
}