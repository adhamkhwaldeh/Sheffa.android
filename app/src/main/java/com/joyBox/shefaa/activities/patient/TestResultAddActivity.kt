package com.joyBox.shefaa.activities.patient

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.View
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.JoyBox.Shefaa.R
import com.github.angads25.filepicker.controller.DialogSelectionListener
import com.github.angads25.filepicker.model.DialogConfigs
import com.github.angads25.filepicker.model.DialogProperties
import com.github.angads25.filepicker.view.FilePickerDialog
import com.joyBox.shefaa.activities.BaseActivity
import com.joyBox.shefaa.di.component.DaggerTestResultAddComponent
import com.joyBox.shefaa.di.module.AttachmentModule
import com.joyBox.shefaa.di.module.TestResultsModule
import com.joyBox.shefaa.di.ui.AttachmentContract
import com.joyBox.shefaa.di.ui.AttachmentPresenter
import com.joyBox.shefaa.di.ui.TestResultsPresenter
import com.joyBox.shefaa.di.ui.TestsResultsContract
import com.joyBox.shefaa.dialogs.ProgressDialog
import com.joyBox.shefaa.entities.responses.UploadFileResponse
import com.joyBox.shefaa.enums.LayoutStatesEnum
import com.joyBox.shefaa.listeners.OnRefreshLayoutListener
import com.joyBox.shefaa.viewModels.TestResultAddViewHolder
import com.joyBox.shefaa.views.Stateslayoutview
import java.io.File
import javax.inject.Inject


class TestResultAddActivity : BaseActivity(), AttachmentContract.View, TestsResultsContract.View,
        DialogSelectionListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var attachmentPresenter: AttachmentPresenter

    @Inject
    lateinit var presenter: TestResultsPresenter

    @BindView(R.id.stateLayout)
    lateinit var stateLayout: Stateslayoutview

    private lateinit var dialog: FilePickerDialog

    private lateinit var testResultAddViewHolder: TestResultAddViewHolder

    var progressDialog: ProgressDialog = ProgressDialog()

    fun initToolBar() {
        toolbar.setTitle(R.string.AddTestResult)
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    fun initDI() {
        val component = DaggerTestResultAddComponent.builder()
                .attachmentModule(AttachmentModule(this))
                .testResultsModule(TestResultsModule(this))
                .build()
        component.inject(this)

        presenter.attachView(this)
        presenter.subscribe()

        attachmentPresenter.attachView(this)
        attachmentPresenter.subscribe()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_result_add_layout)
        ButterKnife.bind(this)
        testResultAddViewHolder = TestResultAddViewHolder(findViewById(android.R.id.content))
        initToolBar()
        initDI()

        stateLayout.setOnRefreshLayoutListener(object : OnRefreshLayoutListener {
            override fun onRefresh() {
                onSaveButtonClick(stateLayout)
            }

            override fun onRequestPermission() {

            }
        })

    }

    private fun openDialog() {
        val properties = DialogProperties()

        properties.selection_mode = DialogConfigs.SINGLE_MODE
        properties.selection_type = DialogConfigs.FILE_SELECT
        properties.root = File(DialogConfigs.DEFAULT_DIR)
        properties.error_dir = File(DialogConfigs.DEFAULT_DIR)
        properties.offset = File(DialogConfigs.DEFAULT_DIR)
        properties.extensions = null
        dialog = FilePickerDialog(this@TestResultAddActivity, properties)
        dialog.setTitle(resources.getString(R.string.SelectAfile))

        dialog.setDialogSelectionListener(this)

        dialog.show()
    }

    @OnClick(R.id.addFileBtn)
    fun onAddFileButtonClick(view: View) {
        openDialog()
    }

    @OnClick(R.id.saveBtn)
    fun onSaveButtonClick(view: View) {
        if (testResultAddViewHolder.isValid())
            presenter.addTestResult(testResultAddModel = testResultAddViewHolder.getTestResultAddModel())
    }

    override fun onSelectedFilePaths(files: Array<out String>?) {
        if (files != null) {
            if (files.isNotEmpty()) {
                attachmentPresenter.uploadFile(filePath = files[0])
            }
        }
        Log.v("", "")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            FilePickerDialog.EXTERNAL_READ_PERMISSION_GRANT -> {
                if ((grantResults.isNotEmpty()) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    dialog.show()
//                    if (dialog != null) {   //Show dialog if the read permission has been granted.
//
//                    }
                } else {
                    //Permission has not been granted. Notify the user.
                    Toast.makeText(TestResultAddActivity@ this, "Permission is Required for getting list of files", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /*Attachment presenter started*/
    override fun showAttachmentProgress(show: Boolean) {
        if (show) {
            progressDialog.show(supportFragmentManager, ProgressDialog.ProgressDialog_Tag)
        } else {
            progressDialog.dismiss()
        }
    }

    override fun showAttachmentLoadErrorMessage(visible: Boolean) {
        if (visible) {
            Toast.makeText(baseContext, R.string.PleaseCheckInternetConnection, Toast.LENGTH_LONG).show()
        }
    }

    override fun uploadFileSuccessfully(uploadFileResponse: UploadFileResponse) {
        testResultAddViewHolder.fileUploadFileResponse = uploadFileResponse
        Toast.makeText(baseContext, R.string.FileUploadedSuccessfully, Toast.LENGTH_LONG).show()
        Log.v("", "")
    }

    override fun uploadFileFailed() {
        Toast.makeText(baseContext, R.string.UploadFileFailed, Toast.LENGTH_LONG).show()
        Log.v("", "")
    }
    /*Attachment presenter ended*/


    /*Add Test presenter started*/
    override fun showProgress(show: Boolean) {
        if (show) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun showLoadErrorMessage(visible: Boolean) {
        if (visible) {
            stateLayout.FlipLayout(LayoutStatesEnum.Waitinglayout)
        } else {
            stateLayout.FlipLayout(LayoutStatesEnum.SuccessLayout)
        }
    }

    override fun onTestResultAddedSuccessfully() {
        Toast.makeText(baseContext, getString(R.string.TestResultAddedSuccessfully), Toast.LENGTH_LONG).show()
        finish()
    }

    override fun onTestResultAddFail() {
        Toast.makeText(baseContext, getString(R.string.AddTestResultFailed), Toast.LENGTH_LONG).show()
    }
    /*Add Test presenter ended*/

}