package com.thunderdogge.messaggio

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

open class MessageReceiver(private val activity: Activity) : IMessageReceiver {
    override fun onMessageReceived(message: IMessage) {
        if (message is ToastMessage) {
            val length = when (message.duration) {
                MessageDuration.Short -> Toast.LENGTH_SHORT
                MessageDuration.Long -> Toast.LENGTH_LONG
            }

            val text = message.resource.format(activity)
            showToastMessage(activity, text, length)

            return
        }

        if (message is SnackbarMessage) {
            val length = when (message.duration) {
                MessageDuration.Short -> Snackbar.LENGTH_SHORT
                MessageDuration.Long -> Snackbar.LENGTH_LONG
            }

            val text = message.resource.format(activity)
            showSnackbarMessage(activity, text, length)

            return
        }

        error("Unexpected message type: ${message::class.simpleName}")
    }

    protected open fun showToastMessage(context: Activity, text: String, length: Int) {
        Toast.makeText(context, text, length).show()
    }

    protected open fun showSnackbarMessage(context: Activity, text: String, length: Int) {
        val view = context.findViewById<View>(android.R.id.content) ?: return
        Snackbar.make(view, text, length).show()
    }
}
