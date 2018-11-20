package com.joyBox.shefaa.helpers

import com.joyBox.shefaa.entities.ReportExpense
import com.joyBox.shefaa.entities.ReportReceipt

/**
 * Created by Adhamkh on 2018-11-20.
 */
class ReportHelper {

    companion object {

        fun totalExpenseReport(list: MutableList<ReportExpense>): Double {
            var total: Double = 0.0
            try {
                list.forEach {
                    try {
                        total += it.how_much.toDouble()
                    } catch (ex: Exception) {
                    }
                }
            } catch (ex: Exception) {

            }
            return total
        }

        fun totalIncomingReport(list: MutableList<ReportReceipt>): Double {
            var total: Double = 0.0
            try {
                list.forEach {
                    try {
                        total += it.Receipt_value.toDouble()
                    } catch (ex: Exception) {
                    }
                }
            } catch (ex: Exception) {

            }
            return total
        }
    }
}