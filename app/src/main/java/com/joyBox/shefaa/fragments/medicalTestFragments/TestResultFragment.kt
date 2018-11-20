package com.joyBox.shefaa.fragments.medicalTestFragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.TestsResultRecyclerViewAdapter
import com.joyBox.shefaa.di.component.DaggerTestResultsComponent
import com.joyBox.shefaa.di.module.TestResultsModule
import com.joyBox.shefaa.di.ui.TestResultsPresenter
import com.joyBox.shefaa.di.ui.TestsResultsContract
import com.joyBox.shefaa.entities.TestResultEntity
import com.joyBox.shefaa.entities.User
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.GridDividerDecoration
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class TestResultFragment : BaseMedicalTestFragment(), TestsResultsContract.View {

    companion object {
        fun getNewInstance(user: User): TestResultFragment {
            val f = TestResultFragment()
            f.titleRes = R.string.TestResult
            f.user = user
            return f
        }
    }

    @Inject
    lateinit var presenter: TestResultsPresenter


    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.addNewAnalise)
    lateinit var addNewAnalise: TextView


    private fun initDI() {
        val component = DaggerTestResultsComponent.builder()
                .testResultsModule(TestResultsModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
//        val client = UserRepository(activity!!).getClient()!!
        presenter.loadTestsResults(/*client.*/user.uid)
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(GridDividerDecoration(context))
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.test_result_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDI()
        initRecyclerView()
        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
//                val client = UserRepository(activity!!).getClient()!!
                presenter.loadTestsResults(/*client.*/user.uid)
            }

            override fun onRequestPermission() {

            }
        })


        val localUser = UserRepository(context!!).getClient()!!.user
        if (localUser.uid == (user.uid)) {
            addNewAnalise.visibility = View.INVISIBLE
        }

    }

    @OnClick(R.id.addNewAnalise)
    fun onAddNewAnalise(view: View) {
        IntentHelper.startTestResultAddActivity(context!!)
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

    override fun onTestsResultsLoadedSuccessfully(testResultEntityList: MutableList<TestResultEntity>) {
        recyclerView.adapter = TestsResultRecyclerViewAdapter(context!!, testResultEntityList.toMutableList())
        Log.v("", "")
    }

    /*Presenter ended*/
}