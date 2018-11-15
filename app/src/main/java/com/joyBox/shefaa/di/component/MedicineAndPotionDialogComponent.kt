package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.di.module.ReminderModule
import com.joyBox.shefaa.di.scope.PerActivity
import com.joyBox.shefaa.dialogs.MedicineAndPotionDialog
import dagger.Component

@PerActivity
@Component(modules = [ReminderModule::class])
interface MedicineAndPotionDialogComponent {
    fun inject(medicineAndPotionDialog: MedicineAndPotionDialog)
}