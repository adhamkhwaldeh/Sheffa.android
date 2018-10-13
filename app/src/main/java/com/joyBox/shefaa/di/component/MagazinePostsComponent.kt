package com.joyBox.shefaa.di.component

import com.joyBox.shefaa.activities.MagazinePostsActivity
import com.joyBox.shefaa.di.module.MagazinePostModule
import com.joyBox.shefaa.di.scope.PerActivity
import dagger.Component

/**
 * Created by Adhamkh on 2018-08-21.
 */
@PerActivity
@Component(modules = [MagazinePostModule::class])
public interface MagazinePostsComponent {
    fun inject(magazinePostsActivity: MagazinePostsActivity)
}