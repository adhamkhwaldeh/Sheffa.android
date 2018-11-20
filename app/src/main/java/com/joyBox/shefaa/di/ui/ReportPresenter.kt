package com.joyBox.shefaa.di.ui

import android.content.Context
import com.joyBox.shefaa.entities.GeneralReport
import com.joyBox.shefaa.entities.PaymentType
import com.joyBox.shefaa.entities.ReportExpense
import com.joyBox.shefaa.entities.ReportReceipt
import com.joyBox.shefaa.entities.models.AppointmentInvoiceModel
import com.joyBox.shefaa.entities.models.IncomingAddModel
import com.joyBox.shefaa.entities.models.PaymentAddModel
import com.joyBox.shefaa.networking.listeners.*
import com.joyBox.shefaa.networking.tasks.*

class ReportPresenter constructor(val context: Context) : ReportContract.Presenter {

    private lateinit var view: ReportContract.View

    init {

    }

    override fun subscribe() {
    }

    override fun attachView(view: ReportContract.View) {
        this.view = view
    }

    override fun loadGeneralReport(year: String, month: String, day: String) {
        ReportGeneralAsync(year, month, day, object : OnReportGeneralListener {
            override fun onReportGeneralLoading() {
                view.showProgress(true)
            }

            override fun onReportGeneralInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onReportGeneralSuccessFully(generalReport: GeneralReport) {
                view.showProgress(false)
                view.onGeneralReportLoaded(generalReport)
            }

            override fun onReportGeneralFail() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()
    }

    override fun loadSpendingReport(startDate: String, endDate: String) {
        ReportSpendingAsync(startDate, endDate, object : OnReportExpenseListener {
            override fun onReportExpenseLoading() {
                view.showProgress(true)
            }

            override fun onReportExpenseInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onReportExpenseSuccessFully(reportExpenseList: List<ReportExpense>) {
                view.showProgress(false)
                view.onSpendingReportLoaded(reportExpenseList.toMutableList())
            }

            override fun onReportExpenseNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun loadIncomingReport(startDate: String, endDate: String) {
        ReportIncomingAsync(startDate, endDate, object : OnReportReceiptListener {
            override fun onReportGeneralLoading() {
                view.showProgress(true)
            }

            override fun onReportGeneralInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onReportGeneralSuccessFully(reportReceiptList: List<ReportReceipt>) {
                view.showProgress(false)
                view.onIncomingReportLoaded(reportReceiptList.toMutableList())
            }

            override fun onReportGeneralNoData() {
                view.showProgress(false)
                view.showEmptyView(true)
            }
        }).execute()

    }

    override fun addSpendingReport(paymentAddModel: PaymentAddModel) {
        PaymentAddAsync(paymentAddModel, object : OnPaymentAddListener {
            override fun onPaymentAddLoading() {
                view.showProgress(true)
            }

            override fun onPaymentAddInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onPaymentAddSuccessFully() {
                view.showProgress(false)
                view.onSpendingAddedSuccessfully()
            }

            override fun onPaymentAddFail() {
                view.showProgress(false)
                view.onAddSpendingFailed()
            }
        }).execute()

    }

    override fun loadPaymentTypes() {
        PaymentTypeAsync(object : OnPaymentTypeListener {
            override fun onPaymentTypeLoading() {
                view.showProgress(true)
            }

            override fun onPaymentTypeInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onPaymentTypeSuccessFully(paymentTypeList: List<PaymentType>) {
                view.showProgress(false)
                view.onPaymentTypesLoaded(paymentTypeList.toMutableList())
            }

            override fun onPaymentTypeNoData() {
                view.showProgress(false)
                view.onAddSpendingFailed()
            }
        }).execute()

    }

    override fun addIncoming(incomingAddModel: IncomingAddModel) {
        DoctorAppointmentInvoiceAsync(incomingAddModel, object : OnDoctorAppointmentInvoiceListener {
            override fun onAppointmentInvoiceLoading() {
                view.showProgress(true)
            }

            override fun onAppointmentInvoiceInternetConnection() {
                view.showProgress(false)
                view.showLoadErrorMessage(true)
            }

            override fun onAppointmentInvoiceSuccessFully() {
                view.showProgress(false)
                view.onIncomingAddedSuccessfully()
            }

            override fun onAppointmentInvoiceFail() {
                view.showProgress(false)
                view.onIncomingAddedFailed()
            }
        }).execute()
    }


    override fun unSubscribe() {

    }

}