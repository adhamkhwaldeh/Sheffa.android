package com.joyBox.shefaa.viewModels

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.entities.models.TestResultAddModel
import com.joyBox.shefaa.entities.responses.UploadFileResponse
import com.joyBox.shefaa.repositories.UserRepository

class TestResultAddViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var context: Context = view.context

    @BindView(R.id.titleEditText)
    lateinit var titleEditText: EditText

    @BindView(R.id.fileName)
    lateinit var fileName: TextView

    var fileUploadFileResponse: UploadFileResponse? = null

    init {
        ButterKnife.bind(this, view)
    }


    fun isValid(): Boolean {
        if (titleEditText.text.isNullOrEmpty()) {
            return false
        }
        if (fileUploadFileResponse == null)
            return false

        return true
    }


    fun getTestResultAddModel(): TestResultAddModel = TestResultAddModel(title = titleEditText.text.toString(),
            patientId = UserRepository(context).getClient()!!.user.uid, doctorId = "2", fId = fileUploadFileResponse!!.fid)

}