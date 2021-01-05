package com.thunderdogge.sample

import android.app.Activity
import android.view.View
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

    override fun getSnackbarContainerView(context: Activity): View? {
        val container: View? = activity.findViewById(R.id.containerLayout)
        return container ?: super.getSnackbarContainerView(context)
    }

    private fun showDialogMessage(message: DialogMessage) {
        MaterialAlertDialogBuilder(activity)
            .setTitle(message.title)
            .setMessage(message.text)
            .setPositiveButton("OK", null)
            .show()
    }
}

class DialogMessage(
    val title: CharSequence,
    val text: CharSequence
) : IMessage

fun IMessenger.showDialog(title: CharSequence, text: CharSequence) {
    val message = DialogMessage(title, text)
    postMessage(message)
}
