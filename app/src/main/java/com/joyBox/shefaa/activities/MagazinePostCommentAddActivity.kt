package com.joyBox.shefaa.activities

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.di.component.DaggerMagazinePostCommentAddComponent
import com.joyBox.shefaa.di.module.MagazinePostCommentModule
import com.joyBox.shefaa.di.ui.MagazinePostCommentsContract
import com.joyBox.shefaa.di.ui.MagazinePostCommentsPresenter
import com.joyBox.shefaa.entities.MagazinePost
import com.joyBox.shefaa.entities.models.MagazinePostCommentAddModel
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class MagazinePostCommentAddActivity : BaseActivity(), MagazinePostCommentsContract.View {

    companion object {
        const val MagazinePostCommentAddActivity_Tag = "MagazinePostCommentAddActivity_Tag"
    }

    @Inject
    lateinit var presenter: MagazinePostCommentsPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.commentTitle)
    lateinit var commentTitle: EditText

    @BindView(R.id.commentBody)
    lateinit var commentBody: EditText

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    private lateinit var magazinePost: MagazinePost


    fun initToolBar() {
        toolbar.setTitle(R.string.addComment)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initDI() {
        val component = DaggerMagazinePostCommentAddComponent.builder()
                .magazinePostCommentModule(MagazinePostCommentModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.magazine_post_comment_add_layout)
        ButterKnife.bind(this)

        val jSon = intent.getStringExtra(MagazinePostCommentAddActivity_Tag)
        magazinePost = Gson().fromJson(jSon, MagazinePost::class.java)

        initToolBar()
        initDI()

    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        if ((!commentTitle.text.toString().isEmpty()) &&
                (!commentBody.text.toString().isEmpty())) {
            val userId = UserRepository(baseContext).getClient()!!.user.uid
            val magazinePostCommentAddModel = MagazinePostCommentAddModel(userId = userId, postId = magazinePost.nid,
                    title = commentTitle.text.toString(), body = commentBody.text.toString())
            presenter.addMagazinePostComment(magazinePostCommentAddModel = magazinePostCommentAddModel)
        }
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible) {
            Toast.makeText(baseContext, R.string.PleaseCheckInternetConnection, Toast.LENGTH_LONG).show()
        } else {

        }
    }

    override fun onAddMagazinePostCommentSuccessfully() {
        Toast.makeText(baseContext, R.string.CommentAddedCorrectly, Toast.LENGTH_LONG).show()
        Handler(mainLooper).postDelayed({
            finish()
        }, 500)
    }

    override fun onAddMagazinePostCommentFail() {
        Toast.makeText(baseContext, R.string.AddCommentFailed, Toast.LENGTH_LONG).show()
    }
    /*Presenter ended*/


}