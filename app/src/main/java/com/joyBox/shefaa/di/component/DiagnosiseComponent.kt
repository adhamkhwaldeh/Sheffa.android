package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.DiagnosisModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.dialogs.DiagnosiseDialog
import dagger.Component


@PerActivity
@Component(modules = [DiagnosisModule::class])
interface DiagnosiseComponent {
    fun inject(diagnosiseDialog: DiagnosiseDialog)
}