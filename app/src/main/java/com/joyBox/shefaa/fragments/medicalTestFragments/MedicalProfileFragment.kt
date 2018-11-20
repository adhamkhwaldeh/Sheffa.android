package com.joyBox.shefaa.fragments.medicalTestFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerMedicalProfileComponent
import com.joyBox.shefaa.di.module.DiagnosisModule
import com.joyBox.shefaa.di.module.MedicalProfileModule
import com.joyBox.shefaa.di.ui.DiagnosiseContract
import com.joyBox.shefaa.di.ui.DiagnosisePresenter
import com.joyBox.shefaa.di.ui.MedicalProfileContract
import com.joyBox.shefaa.di.ui.MedicalProfilePresenter
import com.joyBox.shefaa.entities.DiagnosiseAutoComplete
import com.joyBox.shefaa.entities.MedicalProfile
import com.joyBox.shefaa.entities.User
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.enums.ProfileType
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.repositories.UserRepository
import com.joyBox.shefaa.viewModels.MedicalProfileViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject


class MedicalProfileFragment : BaseMedicalTestFragment(), MedicalProfileContract.View, DiagnosiseContract.View {

    companion object {
        fun getNewInstance(user: User): MedicalProfileFragment {
            val f = MedicalProfileFragment()
            f.titleRes = R.string.MedicalProfile
            f.user = user
            return f
        }
    }


    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @BindView(R.id.diagnosisStateLayout)
    lateinit var diagnosisStateLayout: Stateslayoutview

    @BindView(R.id.saveBtn)
    lateinit var saveBtn: View

    @Inject
    lateinit var presenter: MedicalProfilePresenter

    @Inject
    lateinit var diagnosisPresenter: DiagnosisePresenter


    lateinit var medicalProfileViewHolder: MedicalProfileViewHolder

    private fun initDI() {
        val component = DaggerMedicalProfileComponent.builder()
                .medicalProfileModule(MedicalProfileModule(activity!!))
                .diagnosisModule(DiagnosisModule(activity!!))
                .build()
        component.inject(this)

        diagnosisPresenter.attachView(this)
        diagnosisPresenter.loadDiagnosiseAutoComplete()

        presenter.attachView(this)
        presenter.subscribe()
        val user = UserRepository(activity!!).getClient()
        presenter.loadUserProfile(userId = user!!.user.uid, profileType = ProfileType.PATIENT.type)


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.medical_profile_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        medicalProfileViewHolder = MedicalProfileViewHolder(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
//                val user = UserRepository(activity!!).getClient()
                presenter.updateUserProfile(medicalProfileViewHolder.getUpdateUrl(userId =/* user!!.*/user.uid))
            }

            override fun onRequestPermission() {

            }
        })

        diagnosisStateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                diagnosisPresenter.loadDiagnosiseAutoComplete()
            }

            override fun onRequestPermission() {

            }
        })


        val localUser = UserRepository(context!!).getClient()!!.user
        if (localUser.uid != (user.uid)) {
            saveBtn.visibility = View.INVISIBLE
        } else {
            saveBtn.visibility = View.VISIBLE
        }

    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
//        val user = UserRepository(activity!!).getClient()
        presenter.updateUserProfile(medicalProfileViewHolder.getUpdateUrl(userId = /*user!!.*/user.uid))
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

    override fun onUserProfileLoaded(medicalProfile: MedicalProfile) {
        medicalProfileViewHolder.bind(medicalProfile)
        Log.v("", "")
    }

    override fun onUserProfileUpdateSuccessfully() {
        Toast.makeText(context, R.string.profileUpdateSuccessfully, Toast.LENGTH_LONG).show()
    }

    /*Presenter ended*/


    /*Diagnosis Presenter started*/

    override fun showDiagnosiseAutoCompleteProgress(show: Boolean) {
        if (show) {
            diagnosisStateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            diagnosisStateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showDiagnosiseAutoCompleteEmptyView(visible: Boolean) {
        if (visible) {
            diagnosisStateLayout.FlipLayout(LayoutStatesEnum.Nodatalayout)
        } else {
            diagnosisStateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showDiagnosiseAutoCompleteLoadErrorMessage(visible: Boolean) {
        if (visible) {
            diagnosisStateLayout.FlipLayout(LayoutStatesEnum.Noconnectionlayout)
        } else {
            diagnosisStateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun onDiagnosiseAutoCompleteSuccessfully(diagnosiseAutoCompleteList: MutableList<DiagnosiseAutoComplete>) {
        medicalProfileViewHolder.bindSpinner(diagnosiseAutoCompleteList)
    }
    /*Diagnosis Presenter ended*/

}