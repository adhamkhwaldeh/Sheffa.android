package com.joyBox.shefaa.activities.patient

import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import butterknife.OnTouch
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.di.component.DaggerGuardianshipAddComponent
import com.joyBox.shefaa.di.module.GuardianshipModule
import com.joyBox.shefaa.di.ui.GuardianshipContract
import com.joyBox.shefaa.di.ui.GuardianshipPresenter
import com.joyBox.shefaa.entities.GuardianshipAutoComplete
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.eventsBus.EventActions
import com.joyBox.shefaa.eventsBus.MessageEvent
import com.joyBox.shefaa.eventsBus.RxBus
import com.joyBox.shefaa.helpers.IntentHelper
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.networking.NetworkingHelper
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject

class GuardianshipAddActivity : BaseActivity(), GuardianshipContract.View {

    @Inject
    lateinit var presenter: GuardianshipPresenter

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.guardianshipName)
    lateinit var guardianshipName: EditText

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    fun initDI() {
        val component = DaggerGuardianshipAddComponent.builder()
                .guardianshipModule(GuardianshipModule(this))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.AddGuardianship)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.guardianship_add_layout)
        ButterKnife.bind(this)

        initToolbar()
        initDI()

        RxBus.listen(MessageEvent::class.java).subscribe {
            when (it.action) {
                EventActions.GuardianshipAutoCompleteActivity_Tag -> {
                    val guardianshipAutoComplete: GuardianshipAutoComplete = it.message as GuardianshipAutoComplete
                    val name = guardianshipAutoComplete.first_Name + " " + guardianshipAutoComplete.last_Name
                    guardianshipName.setText(name)
                    guardianshipName.tag = name//guardianshipAutoComplete.username
                }
            }
        }

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                onSaveButtonClick(stateLayout)
            }

            override fun onRequestPermission() {

            }
        })
    }

    @OnTouch(R.id.guardianshipName)
    fun onGuardianshipNameTouch(view: View, motionEvent: MotionEvent): Boolean {
        if (motionEvent.action != KeyEvent.ACTION_DOWN) {
            return false
        }
        IntentHelper.startGuardianshipAutoCompleteActivity(baseContext)
        return false
    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        if (!guardianshipName.text.isNullOrEmpty()) {
            val userId = UserRepository(baseContext).getClient()!!.user.uid
            val url: String = NetworkingHelper.GuardianshipAddUrl + userId + "/relationship/" + guardianshipName.tag.toString()
            presenter.addGuardianship(url)
        }
    }

    /*Guardianship presenter started*/
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

    override fun onGuardianshipAddedSuccessfully() {
        Toast.makeText(baseContext, baseContext.resources.getString(R.string.GuardianshipAddedSuccessfully), Toast.LENGTH_LONG).show()
    }

    override fun onGuardianshipAddedFail() {
        super.onGuardianshipAddedFail()
        Toast.makeText(baseContext, baseContext.resources.getString(R.string.AddGuardianshipFailed), Toast.LENGTH_LONG).show()
    }
    /*Guardianship presenter ended*/
}