package com.joyBox.shefaa.activities

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.google.gson.Gson
import com.joyBox.shefaa.adapters.MagazinePostCommentsRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerMagazinePostCommentsComponent
import com.joyBox.shefaa.di.component.DaggerMagazinePostsComponent
import com.joyBox.shefaa.di.module.MagazinePostCommentModule
import com.joyBox.shefaa.di.module.MagazinePostModule
import com.joyBox.shefaa.di.ui.MagazinePostCommentsContract
import com.joyBox.shefaa.di.ui.MagazinePostCommentsPresenter
import com.joyBox.shefaa.entities.MagazinePost
import com.joyBox.shefaa.entities.MagazinePostComment
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class MagazinePostCommentsActivity : BaseActivity(), MagazinePostCommentsContract.View {

    companion object {
        const val MagazinePostComments_Post_Tag = "MagazinePostComments_Post_Tag"
    }

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var presenter: MagazinePostCommentsPresenter

    private lateinit var magazinePost: MagazinePost

    private fun initDI() {
        val component = DaggerMagazinePostCommentsComponent.builder()
                .magazinePostCommentModule(MagazinePostCommentModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadMagazinePostComments(magazinePost.nid)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(GridDividerDecoration(this))
    }

    private fun initToolBar() {
        toolbar.setTitle(R.string.PostComments)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.magazine_post_comments_layout)
        ButterKnife.bind(this)

        val jSon = intent.getStringExtra(MagazinePostComments_Post_Tag)
        magazinePost = Gson().fromJson(jSon, MagazinePost::class.java)

        initToolBar()
        initRecyclerView()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                presenter.loadMagazinePostComments(postId = magazinePost.nid)
            }

            override fun onRequestPermission() {

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.magazine_post_comments_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.comment_add_action -> {
                IntentHelper.startMagazinePostCommentAddActivity(this, magazinePost)
            }
        }
        return true
    }

    /*Presenter started*/
    override fun showProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showEmptyView(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun onMagazinePostCommentsSuccessfully(magazinePostCommentsList: List<MagazinePostComment>) {
        recyclerView.adapter = MagazinePostCommentsRecyclerViewAdapter(context = baseContext,
                magazinePostCommentList = magazinePostCommentsList)
        Log.v("", "")
    }
    /*Presenter ended*/

}