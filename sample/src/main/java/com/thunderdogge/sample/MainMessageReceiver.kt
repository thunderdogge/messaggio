package com.thunderdogge.sample

import android.app.Activity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.thunderdogge.messaggio.IMessage
import com.thunderdogge.messaggio.IMessenger
import com.thunderdogge.messaggio.MessageReceiver

class MainMessageReceiver(private val activity: Activity) : MessageReceiver(activity) {
    override fun onMessageReceived(message: IMessage) {
        if (message is DialogMessage) {
            showDialogMessage(message)
            return
        }

        super.onMessageReceived(message)
    }

    private fun showDialogMessage(message: DialogMessage) {
        MaterialAlertDialogBuilder(activity)
            .setTitle(message.title)
            .setMessage(message.text)
            .setPositiveButton("OK", null)
            .show()
    }
}

class DialogMessage(val title: String, val text: String) : IMessage

fun IMessenger.showDialog(title: String, text: String) {
    val message = DialogMessage(title, text)
    postMessage(message)
}
