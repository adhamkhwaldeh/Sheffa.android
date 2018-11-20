package com.joyBox.shefaa.di.ui

import com.joyBox.shefaa.entities.GeneralReport
import com.joyBox.shefaa.entities.PaymentType
import com.joyBox.shefaa.entities.ReportExpense
import com.joyBox.shefaa.entities.ReportReceipt
import com.joyBox.shefaa.entities.models.AppointmentInvoiceModel
import com.joyBox.shefaa.entities.models.IncomingAddModel
import com.joyBox.shefaa.entities.models.PaymentAddModel

class ReportContract {
    interface Presenter : BaseContract.Presenter<View> {
        fun loadGeneralReport(year: String, month: String, day: String)
        fun loadSpendingReport(startDate: String, endDate: String)
        fun loadIncomingReport(startDate: String, endDate: String)
        fun addSpendingReport(paymentAddModel: PaymentAddModel)
        fun loadPaymentTypes()
        fun addIncoming(incomingAddModel: IncomingAddModel)
    }

    interface View : BaseContract.View {
        fun onGeneralReportLoaded(generalReport: GeneralReport) {}
        fun onSpendingReportLoaded(reportExpenseList: MutableList<ReportExpense>) {}
        fun onIncomingReportLoaded(reportReceiptList: MutableList<ReportReceipt>) {}

        fun onSpendingAddedSuccessfully() {}
        fun onAddSpendingFailed() {}

        fun onPaymentTypesLoaded(paymentTypeList: MutableList<PaymentType>) {}

        fun onIncomingAddedSuccessfully() {}
        fun onIncomingAddedFailed() {}

    }

}