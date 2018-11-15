package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.MagazinePostsDetailsActivity
import com.joyBox.shefaa.di.module.MagazinePostModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component


@PerActivity
@Component(modules = [MagazinePostModule::class])
interface MagazinePostsDetailsComponent {
    fun inject(magazinePostsDetailsActivity: MagazinePostsDetailsActivity)
}