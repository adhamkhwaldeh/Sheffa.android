package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.MagazinePostCommentsActivity
import com.joyBox.shefaa.activities.MagazinePostsDetailsActivity
import com.joyBox.shefaa.di.module.MagazinePostCommentModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-21.
 */
@PerActivity
@Component(modules = [MagazinePostCommentModule::class])
public interface MagazinePostCommentsComponent {
    fun inject(magazinePostCommentsActivity: MagazinePostCommentsActivity)
}