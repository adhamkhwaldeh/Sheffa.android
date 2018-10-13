package com.joyBox.shefaa.networking.listeners

import com.joyBox.shefaa.entities.MagazinePost

/**
 * Created by Adhamkh on 2018-08-21.
 */
interface OnMagazinePostsResponseListener {
    fun onMagazinePostsResponseLoading()

    fun onMagazinePostsResponseInternetConnection()

    fun onMagazinePostsResponseSuccessFully(magazinePostsList: List<MagazinePost>)

    fun onMagazinePostsResponseNoData()
}