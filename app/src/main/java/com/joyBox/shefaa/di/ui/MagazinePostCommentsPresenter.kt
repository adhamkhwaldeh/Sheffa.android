package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.MagazinePostComment
import com.joyBox.shefaa.entities.models.MagazinePostCommentAddModel
import com.joyBox.shefaa.networking.listeners.OnMagazinePostCommentAddListener
import com.joyBox.shefaa.networking.listeners.OnMagazinePostCommentsResponseListener
import com.joyBox.shefaa.networking.tasks.MagazinePostCommentAddAsync
import com.joyBox.shefaa.networking.tasks.MagazinePostCommentsAsync
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Adhamkh on 2018-08-21.
 */
class MagazinePostCommentsPresenter constructor(val context: Context) : MagazinePostCommentsContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: MagazinePostCommentsContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: MagazinePostCommentsContract.View) {
        this.view = view
    }

    override fun loadMagazinePostComments(postId: String) {
        MagazinePostCommentsAsync(postId, object : OnMagazinePostCommentsResponseListener {
            override fun onMagazinePostCommentsResponseLoading() {
                view.showProgress(true)
            }

            override fun onMagazinePostCommentsResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onMagazinePostCommentsResponseSuccessFully(magazinePostCommentList: List<MagazinePostComment>) {
                view.showProgress(false)
                view.onMagazinePostCommentsSuccessfully(magazinePostCommentList)
            }

            override fun onMagazinePostCommentsResponseNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun addMagazinePostComment(magazinePostCommentAddModel: MagazinePostCommentAddModel) {
        MagazinePostCommentAddAsync(magazinePostCommentAddModel, object : OnMagazinePostCommentAddListener {
            override fun onMagazinePostCommentAddResponseLoading() {
                view.showProgress(true)
            }

            override fun onMagazinePostCommentAddResponseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onMagazinePostCommentAddResponseSuccessFully() {
                view.showProgress(false)
                view.onAddMagazinePostCommentSuccessfully()
            }

            override fun onMagazinePostCommentAddResponseFail() {
                view.showProgress(false)
                view.onAddMagazinePostCommentFail()
            }
        }).execute()
    }

    override fun unSubscribe() {

    }

}