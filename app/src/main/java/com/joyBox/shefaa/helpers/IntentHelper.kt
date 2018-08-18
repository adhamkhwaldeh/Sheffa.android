package com.joyBox.shefaa.helpers

import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import com.joyBox.shefaa.activities.*
import com.joyBox.shefaa.entities.MessageEntity
import com.joyBox.shefaa.entities.models.MessageReplayModel

/**
 * Created by Adhamkh on 2018-08-10.
 */
class IntentHelper {

    companion object {
        fun startSignInActivity(context: Context) {
            val intent = Intent(context, SignInActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
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
            val intent = Intent(context, ProfileActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }

}