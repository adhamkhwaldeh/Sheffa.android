package com.joyBox.shefaa.activities

import android.opengl.Visibility
import android.os.Build
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.text.Html
import android.view.View
import android.widget.*
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.di.component.DaggerMagazinePostsDetailsComponent
import com.joyBox.shefaa.di.module.MagazinePostModule
import com.joyBox.shefaa.di.ui.MagazinePostsContract
import com.joyBox.shefaa.di.ui.MagazinePostsPresenter
import com.joyBox.shefaa.entities.MagazinePost
import com.joyBox.shefaa.enums.FlagType
import com.joyBox.shefaa.enums.ImageSize
import com.joyBox.shefaa.helpers.BindingHelper
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepositoy
import javax.inject.Inject

class MagazinePostsDetailsActivity : BaseActivity(), MagazinePostsContract.View {

    companion object {
        const val MagazinePostsDetails_Post_Tag = "MagazinePostsDetails_Post_Tag"
    }

    @Inject
    lateinit var presenter: MagazinePostsPresenter

    private lateinit var magazinePost: MagazinePost

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.magazinePostImage)
    lateinit var magazinePostImage: ImageView

    @BindView(R.id.magazinePostBody)
    lateinit var magazinePostBody: TextView

    private fun initDI() {
        val component = DaggerMagazinePostsDetailsComponent.builder()
                .magazinePostModule(MagazinePostModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
//        presenter.loadMagazinePostsList(NetworkingHelper.MagazinePostsUrl)
    }

    fun initToolBar() {
        toolbar.title = (magazinePost.title)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun bindPost() {
        BindingHelper.loadPostImage(magazinePostImage, ImageSize.Large, magazinePost)
//        magazinePostBody.text = (magazinePost.body)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            magazinePostBody.text = (Html.fromHtml(magazinePost.body, Html.FROM_HTML_MODE_COMPACT))
        } else {
            magazinePostBody.text = (Html.fromHtml(magazinePost.body))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.magazine_post_details_layout)
        ButterKnife.bind(this)

        val gSon = intent.getStringExtra(MagazinePostsDetails_Post_Tag)
        magazinePost = Gson().fromJson(gSon, MagazinePost::class.java)

        initToolBar()
        bindPost()
        initDI()


    }


    @OnClick(R.id.magazinePostLikeBtn)
    fun onMagazinePostLikeButtonClick(view: View) {
        val userId: String = UserRepositoy(baseContext).getClient()!!.user.uid
        presenter.likeMagazinePost(magazinePostId = magazinePost.nid, userId = userId, flag = FlagType.Flag.type)
    }

    @OnClick(R.id.magazinePostCommentsBtn)
    fun onMagazinePostCommentsButtonClick(view: View) {
        IntentHelper.startMagazinePostCommentsActivity(baseContext, magazinePost)
    }


    @OnClick(R.id.magazinePostShareBtn)
    fun onMagazinePostShareButtonClick(view: View) {
        IntentHelper.startShareLink(context = baseContext, title = magazinePost.title,
                url = NetworkingHelper.MagazinePostShareUrl + magazinePost.nid)
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {
//        if (show) {
//            val progressBar = ProgressBar(this)
//            progressBar.visibility = View.VISIBLE
//        } else {
//
//        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible)
            Toast.makeText(baseContext, R.string.PleaseCheckInternetConnection, Toast.LENGTH_LONG).show()

    }

    override fun onLikeMagazinePostSuccessfully() {
        super.onLikeMagazinePostSuccessfully()
        Toast.makeText(baseContext, R.string.PostLikedSuccessfully, Toast.LENGTH_LONG).show()
    }

    override fun onLikeMagazinePostFail() {
        Toast.makeText(baseContext, R.string.PostLikedFailed, Toast.LENGTH_LONG).show()
    }
    /*Presenter ended*/
}