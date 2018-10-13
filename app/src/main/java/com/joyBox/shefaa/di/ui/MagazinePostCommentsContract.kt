package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.MagazinePostComment
import com.joyBox.shefaa.entities.models.MagazinePostCommentAddModel

/**
 * Created by Adhamkh on 2018-08-21.
 */
class MagazinePostCommentsContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadMagazinePostComments(postId: String)
        fun addMagazinePostComment(magazinePostCommentAddModel: MagazinePostCommentAddModel)
    }

    interface View : BaseContract.View {
        fun onMagazinePostCommentsSuccessfully(magazinePostCommentsList: List<MagazinePostComment>){}

        fun onAddMagazinePostCommentSuccessfully() {}
        fun onAddMagazinePostCommentFail() {}
    }
}