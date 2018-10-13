package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.MagazinePostCommentAddActivity
import com.joyBox.shefaa.di.module.MagazinePostCommentModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [MagazinePostCommentModule::class])
public interface MagazinePostCommentAddComponent {
    fun inject(magazinePostCommentAddActivity: MagazinePostCommentAddActivity)
}