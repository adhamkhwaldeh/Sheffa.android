package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.MagazinePost

/**
 * Created by Adhamkh on 2018-08-21.
 */
class MagazinePostsContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadMagazinePostsList(url: String)
        fun likeMagazinePost(magazinePostId: String, userId: String, flag: String)
    }

    interface View : BaseContract.View {
        fun onMagazinePostsSuccessfully(magazinePostsList: List<MagazinePost>){}

        fun onLikeMagazinePostSuccessfully(){}
        fun onLikeMagazinePostFail(){}
    }
}