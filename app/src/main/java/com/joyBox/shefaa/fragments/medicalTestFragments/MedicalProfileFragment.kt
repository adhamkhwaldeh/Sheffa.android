package com.joyBox.shefaa.fragments.medicalTestFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.di.component.DaggerMedicalProfileComponent
import com.joyBox.shefaa.di.module.MedicalProfileModule
import com.joyBox.shefaa.di.ui.MedicalProfileContract
import com.joyBox.shefaa.di.ui.MedicalProfilePresenter
import com.joyBox.shefaa.entities.MedicalProfile
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.enums.ProfileType
import com.joyBox.shefaa.repositories.UserRepositoy
import com.joyBox.shefaa.viewModels.MedicalProfileViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import javax.inject.Inject


class MedicalProfileFragment : BaseMedicalTestFragment(), MedicalProfileContract.View {

    companion object {
        fun getNewInstance(): MedicalProfileFragment {
            val f = MedicalProfileFragment()
            f.titleRes = R.string.MedicalProfile
            return f
        }
    }

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    @Inject
    lateinit var presenter: MedicalProfilePresenter

    lateinit var medicalProfileViewHolder: MedicalProfileViewHolder

    private fun initDI() {
        val component = DaggerMedicalProfileComponent.builder()
                .medicalProfileModule(MedicalProfileModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        val user = UserRepositoy(activity!!).getClient()
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
    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        val user = UserRepositoy(activity!!).getClient()
        presenter.updateUserProfile(medicalProfileViewHolder.getUpdateUrl(userId = user!!.user!!.uid))
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

    }

    /*Presenter ended*/
}