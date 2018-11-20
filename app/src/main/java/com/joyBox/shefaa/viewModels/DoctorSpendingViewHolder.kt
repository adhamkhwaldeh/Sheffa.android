package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.PaymentType
import com.joyBox.shefaa.entities.models.PaymentAddModel

class DoctorSpendingViewHolder constructor(view: View) : RecyclerView.ViewHolder(view) {

    val context: Context = view.context

    @BindView(R.id.addressEditText)
    lateinit var addressEditText: EditText

    @BindView(R.id.paymentTypeSpinner)
    lateinit var paymentTypeSpinner: Spinner

    @BindView(R.id.costEditText)
    lateinit var costEditText: EditText

    @BindView(R.id.dateTextView)
    lateinit var dateTextView: TextView

    @BindView(R.id.notesEditText)
    lateinit var notesEditText: EditText

    init {
        ButterKnife.bind(this, view)
    }


    fun isValid(): Boolean {
        val paymentAddModel = getPaymentModel()
        if (paymentAddModel.title.isBlank()) {
            return false
        }
        if (paymentAddModel.date.isBlank()) {
            return false
        }
        if (paymentAddModel.cost.isBlank()) {
            return false
        }
        if (paymentAddModel.paymentType.tid.isBlank()) {
            return false
        }
        return true
    }

    fun getPaymentModel(): PaymentAddModel {
        var paymentType = PaymentType()
        if (paymentTypeSpinner.selectedItem != null) {
            paymentType = paymentTypeSpinner.selectedItem as PaymentType
        }
        return PaymentAddModel(title = addressEditText.text.toString(),
                date = dateTextView.text.toString(), cost = costEditText.text.toString(),
                desc = notesEditText.text.toString(), paymentType = paymentType)
//        val paymentAddModel = PaymentAddModel(title = addressEditText.text.toString(),
//                date = dateTextView.text.toString(), cost = costEditText.text.toString(), desc = notesEditText.text.toString(),
//                paymentType = paymentType)
//
//        return paymentAddModel
    }


}