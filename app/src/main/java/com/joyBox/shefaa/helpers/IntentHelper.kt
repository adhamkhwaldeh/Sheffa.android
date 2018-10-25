package com.joyBox.shefaa.helpers

import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import com.joyBox.shefaa.activities.*
import com.joyBox.shefaa.activities.patient.*
import com.joyBox.shefaa.entities.*
import com.joyBox.shefaa.entities.models.MessageReplayModel
import com.joyBox.shefaa.enums.ReminderType
import android.support.v4.content.ContextCompat.startActivity
import com.JoyBox.Shefaa.R
import com.joyBox.shefaa.activities.doctor.DoctorAddPrescriptionActivity
import com.joyBox.shefaa.activities.doctor.DoctorAppointmentActivity
import com.joyBox.shefaa.activities.doctor.DoctorDashBoardActivity
import com.joyBox.shefaa.activities.doctor.DoctorProfileActivity


class IntentHelper {

    companion object {
        fun startSignInActivity(context: Context) {
            val intent = Intent(context, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP
            context.startActivity(intent)
        }

        fun startSignUpActivity(context: Context) {
            val intent = Intent(context, SignUpActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startForgotPasswordActivity(context: Context) {
            val intent = Intent(context, ForgotPasswordActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startMainActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startNotificationsActivity(context: Context) {
            val intent = Intent(context, NotificationsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startMagazinesActivity(context: Context) {
            val intent = Intent(context, MagazinePostsActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startMessagesActivity(context: Context) {
            val intent = Intent(context, MessagesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startMessageDetailsActivity(context: Context, messageEntity: MessageEntity) {
            val intent = Intent(context, MessageDetailsActivity::class.java)
            intent.putExtra(MessageDetailsActivity.MessageDetailsActivity_Tag, Gson().toJson(messageEntity))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startReplayMessageActivity(context: Context, messageReplayModel: MessageReplayModel) {
            val intent = Intent(context, MessageReplayActivity::class.java)
            intent.putExtra(MessageReplayActivity.ReplayMessageActivity_Tag, Gson().toJson(messageReplayModel))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startProfileActivity(context: Context) {
            val intent = Intent(context, PatientProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startAppointmentListPatientActivity(context: Context) {
            val intent = Intent(context, AppointmentListPatientActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startMyMedicalProfileActivity(context: Context) {
            val intent = Intent(context, MyMedicalProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startPrescriptionDetailsActivity(context: Context, prescription: Prescription) {
            val intent = Intent(context, PrescriptionDetailsActivity::class.java)
            intent.putExtra(PrescriptionDetailsActivity.Prescription_Tag, Gson().toJson(prescription))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startMagazinePostsDetailsActivity(context: Context, magazinePost: MagazinePost) {
            val intent = Intent(context, MagazinePostsDetailsActivity::class.java)
            intent.putExtra(MagazinePostsDetailsActivity.MagazinePostsDetails_Post_Tag, Gson().toJson(magazinePost))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startMagazinePostCommentsActivity(context: Context, magazinePost: MagazinePost) {
            val intent = Intent(context, MagazinePostCommentsActivity::class.java)
            intent.putExtra(MagazinePostCommentsActivity.MagazinePostComments_Post_Tag, Gson().toJson(magazinePost))
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startDoctorSearchActivity(context: Context) {
            val intent = Intent(context, DoctorSearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startAppointmentDetailsActivity(context: Context, appointment: AppointmentEntity) {
            val intent = Intent(context, AppointmentDetailsActivity::class.java)
            val json = Gson().toJson(appointment)
            intent.putExtra(AppointmentDetailsActivity.AppointmentDetailsActivity_Appointment_Tag, json)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startTestResultAddActivity(context: Context) {
            val intent = Intent(context, TestResultAddActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startAdvanceSearchActivity(context: Context) {
            val intent = Intent(context, AdvanceSearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startDoctorDetailsActivity(context: Context, doctor: Doctor) {
            val intent = Intent(context, DoctorDetailsActivity::class.java)
            val json = Gson().toJson(doctor)
            intent.putExtra(DoctorDetailsActivity.DoctorDetailsActivity_Tag, json)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startAppointmentAddActivity(context: Context, doctor: Doctor) {
            val intent = Intent(context, AppointmentAddActivity::class.java)
            val json = Gson().toJson(doctor)
            intent.putExtra(AppointmentAddActivity.AppointmentAddActivity_Tag, json)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startAppointmentReserveActivity(context: Context) {
            val intent = Intent(context, AppointmentReserveActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startDashBoardActivity(context: Context) {
            val intent = Intent(context, DashBoardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startGuardianshipActivity(context: Context) {
            val intent = Intent(context, GuardianshipActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startSelfMonitorActivity(context: Context) {
            val intent = Intent(context, SelfMonitorActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startMedicineAndPotionReminderActivity(context: Context, reminderType: ReminderType) {
            val intent = Intent(context, MedicineAndPotionReminderActivity::class.java)
            intent.putExtra(MedicineAndPotionReminderActivity.Medicine_And_Potion_Reminder_Tag, reminderType.type)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startShareLink(context: Context, title: String, url: String) {
            val share = Intent(android.content.Intent.ACTION_SEND)
            share.type = "text/plain"
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)

            // Add data to the intent, the receiving app will decide
            // what to do with it.
            share.putExtra(Intent.EXTRA_SUBJECT, title)
            share.putExtra(Intent.EXTRA_TEXT, url)

            context.startActivity(Intent.createChooser(share, context.resources.getString(R.string.ShareLink)))
        }

        fun startMagazinePostCommentAddActivity(context: Context, magazinePost: MagazinePost) {
            val intent = Intent(context, MagazinePostCommentAddActivity::class.java)
            val jSon = Gson().toJson(magazinePost)
            intent.putExtra(MagazinePostCommentAddActivity.MagazinePostCommentAddActivity_Tag, jSon)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startSelfMonitorAddActivity(context: Context) {
            val intent = Intent(context, SelfMonitorAddActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startGuardianshipAddActivity(context: Context) {
            val intent = Intent(context, GuardianshipAddActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startChangeEmailActivity(context: Context) {
            val intent = Intent(context, ChangeEmailActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startChangePasswordActivity(context: Context) {
            val intent = Intent(context, ChangePasswordActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startPharmacySearchActivity(context: Context) {
            val intent = Intent(context, PharmacySearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startPharmacyDetailsActivity(context: Context, pharmacy: Pharmacy) {
            val intent = Intent(context, PharmacyDetailsActivity::class.java)
            val jSon = Gson().toJson(pharmacy)
            intent.putExtra(PharmacyDetailsActivity.PharmacyDetailsActivity_Tag, jSon)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startLabSearchActivity(context: Context) {
            val intent = Intent(context, LabSearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startLabDetailsActivity(context: Context, lab: Lab) {
            val intent = Intent(context, LabDetailsActivity::class.java)
            val jSon = Gson().toJson(lab)
            intent.putExtra(LabDetailsActivity.LabDetailsActivity_Tag, jSon)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startMessageAddActivity(context: Context) {
            val intent = Intent(context, MessageAddActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }


        fun startDoctorDashBoardActivity(context: Context) {
            val intent = Intent(context, DoctorDashBoardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startDoctorProfileActivity(context: Context) {
            val intent = Intent(context, DoctorProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startDoctorAppointmentActivity(context: Context) {
            val intent = Intent(context, DoctorAppointmentActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

        fun startDoctorAddPrescriptionActivity(context: Context, doctorAppointment: DoctorAppointment) {
            val intent = Intent(context, DoctorAddPrescriptionActivity::class.java)
            val jSon = Gson().toJson(doctorAppointment)
            intent.putExtra(DoctorAddPrescriptionActivity.DoctorAddPrescriptionActivity_Tag, jSon)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }

}