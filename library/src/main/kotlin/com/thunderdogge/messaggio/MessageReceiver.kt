package com.thunderdogge.messaggio

import android.app.Activity
import android.view.View
import android.view.Window
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

open class MessageReceiver(private val activity: Activity) : IMessageReceiver {
    override fun onMessageReceived(message: IMessage) {
        if (message is ToastMessage) {
            showToastMessage(activity, message.resource, message.duration)
            return
        }

        if (message is SnackbarMessage) {
            showSnackbarMessage(activity, message.resource, message.duration)
            return
        }

        error("Unexpected message type: ${message::class.simpleName}. Did you forget to implement your own MessageReceiver?")
    }

    protected open fun showToastMessage(context: Activity, resource: IMessageResource, duration: IMessageDuration) {
        val text = resource.format(activity)
        val length = duration.get(activity)
        Toast.makeText(context, text, length).show()
    }

    protected open fun showSnackbarMessage(context: Activity, resource: IMessageResource, duration: IMessageDuration) {
        val view = getSnackbarContainerView(context) ?: error("Snackbar container view is null")
        val text = resource.format(activity)
        val length = duration.get(activity)
        Snackbar.make(view, text, length).show()
    }

    protected open fun getSnackbarContainerView(context: Activity): View? {
        return context.findViewById(Window.ID_ANDROID_CONTENT)
    }
}
