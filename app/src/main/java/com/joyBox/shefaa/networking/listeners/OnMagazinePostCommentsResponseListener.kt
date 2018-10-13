package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.MagazinePostComment

/**
 * Created by Adhamkh on 2018-08-21.
 */
interface OnMagazinePostCommentsResponseListener {
    fun onMagazinePostCommentsResponseLoading();

    fun onMagazinePostCommentsResponseInternetConnection()

    fun onMagazinePostCommentsResponseSuccessFully(magazinePostCommentList: List<MagazinePostComment>)

    fun onMagazinePostCommentsResponseNoData()
}