package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.MagazinePost
import com.joyBox.shefaa.networking.listeners.OnMagazinePostLikeResponseListener
import com.joyBox.shefaa.networking.listeners.OnMagazinePostsResponseListener
import com.joyBox.shefaa.networking.tasks.MagazinePostLikeAsync
import com.joyBox.shefaa.networking.tasks.MagazinePostsAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-08-21.
 */
class MagazinePostsPresenter constructor(val context: Context) : MagazinePostsContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: MagazinePostsContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: MagazinePostsContract.View) {
        this.view = view
    }

    override fun loadMagazinePostsList(url: String) {
        MagazinePostsAsync(url, object : OnMagazinePostsResponseListener {
            override fun onMagazinePostsResponseLoading() {
                view.showProgress(true)
            }

            override fun onMagazinePostsResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onMagazinePostsResponseSuccessFully(magazinePostsList: List<MagazinePost>) {
                view.showProgress(false)
                view.onMagazinePostsSuccessfully(magazinePostsList)
            }

            override fun onMagazinePostsResponseNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun likeMagazinePost(magazinePostId: String, userId: String, flag: String) {
        MagazinePostLikeAsync(magazinePostId, userId, flag, object : OnMagazinePostLikeResponseListener {
            override fun onMagazinePostLikeResponseLoading() {
                view.showProgress(true)
            }

            override fun onMagazinePostLikeResponseInternetConnection() {
                view.showLoadErrorMessage(true)
            }

            override fun onMagazinePostLikeResponseSuccessFully() {
                view.onLikeMagazinePostSuccessfully()
            }

            override fun onMagazinePostLikeResponseFail() {
                view.onLikeMagazinePostFail()
            }
        }).execute()
    }

    override fun unSubscribe() {

    }

}