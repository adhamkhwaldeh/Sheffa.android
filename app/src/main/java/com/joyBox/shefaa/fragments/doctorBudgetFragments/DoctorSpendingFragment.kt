package com.joyBox.shefaa.fragments.doctorBudgetFragments

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.adapters.PaymentTypeSpinnerAdapter
import com.joyBox.shefaa.di.component.DaggerDoctorSpendingComponent
import com.joyBox.shefaa.di.module.ReportModule
import com.joyBox.shefaa.di.ui.ReportContract
import com.joyBox.shefaa.di.ui.ReportPresenter
import com.joyBox.shefaa.entities.PaymentType
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.viewModels.DoctorSpendingViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class DoctorSpendingFragment : BaseDoctorBudgetFragment(), ReportContract.View {

    companion object {
        fun getNewInstance(): BaseDoctorBudgetFragment {
            val f = DoctorSpendingFragment()
            f.titleRes = R.string.Spending
            return f
        }
    }

    @Inject
    lateinit var presenter: ReportPresenter

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    lateinit var doctorSpendingViewHolder: DoctorSpendingViewHolder


    var isTypeLoading: Boolean = true

    fun initDI() {
        val component = DaggerDoctorSpendingComponent.builder()
                .reportModule(ReportModule(activity!!))
                .build()
        component.inject(this)
        presenter.attachView(this)
        presenter.subscribe()
        presenter.loadPaymentTypes()
    }

    private fun initDatePicker(viewClicked: View) {
        var calendar = Calendar.getInstance()
        val timePicker = DatePickerDialog(context, R.style.AppTheme_DialogSlideAnimwithback,
                DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                    calendar = Calendar.getInstance()
                    calendar.set(Calendar.YEAR, year)
                    calendar.set(Calendar.MONTH, month)
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                    val date = calendar.time
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                    val sdt = sdf.format(date)
                    doctorSpendingViewHolder.dateTextView.text = sdt
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        timePicker.show()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.doctor_spending_fragment_layout, container, false)
        ButterKnife.bind(this, view)
        doctorSpendingViewHolder = DoctorSpendingViewHolder(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initDI()
        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                if (isTypeLoading)
                    presenter.loadPaymentTypes()
                else
                    onSaveButtonClick(stateLayout)
            }

            override fun onRequestPermission() {

            }
        })
    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        if (doctorSpendingViewHolder.isValid())
            presenter.addSpendingReport(doctorSpendingViewHolder.getPaymentModel())
    }

    @OnClick(R.id.calenderIcon)
    fun onPaymentDateContainerClick(view: View) {
        initDatePicker(view)
    }


    /*Doctor spending presenter started*/
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

    override fun onPaymentTypesLoaded(paymentTypeList: MutableList<PaymentType>) {
        doctorSpendingViewHolder.paymentTypeSpinner.adapter = PaymentTypeSpinnerAdapter(context!!, paymentTypeList)
        isTypeLoading = false
    }

    override fun onSpendingAddedSuccessfully() {
        Toast.makeText(context, R.string.SpendingAddSuccessfully, Toast.LENGTH_LONG).show()
    }

    override fun onAddSpendingFailed() {
        Toast.makeText(context, R.string.UnexpectedError, Toast.LENGTH_LONG).show()
    }
    /*Doctor spending presenter ended*/
}