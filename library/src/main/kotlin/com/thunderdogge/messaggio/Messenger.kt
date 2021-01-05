package com.thunderdogge.messaggio

open class Messenger(private val dispatcher: MessageDispatcher) : IMessenger {
    fun showToast(text: String, duration: MessageDuration = MessageDuration.Long) {
        val message = ToastMessage(MessageResource.Text(text), duration)
        postMessage(message)
    }

    fun showToast(textResId: Int, duration: MessageDuration = MessageDuration.Long) {
        val message = ToastMessage(MessageResource.Id(textResId), duration)
        postMessage(message)
    }

    fun showToast(textResId: Int, vararg values: Any, duration: MessageDuration = MessageDuration.Long) {
        val message = ToastMessage(MessageResource.Format(textResId, *values), duration)
        postMessage(message)
    }

    fun showSnackbar(text: String, duration: MessageDuration = MessageDuration.Long) {
        val message = SnackbarMessage(MessageResource.Text(text), duration)
        postMessage(message)
    }

    fun showSnackbar(textResId: Int, duration: MessageDuration = MessageDuration.Long) {
        val message = SnackbarMessage(MessageResource.Id(textResId), duration)
        postMessage(message)
    }

    fun showSnackbar(textResId: Int, vararg values: Any, duration: MessageDuration = MessageDuration.Long) {
        val message = SnackbarMessage(MessageResource.Format(textResId, *values), duration)
        postMessage(message)
    }

    override fun postMessage(message: IMessage) {
        dispatcher.dispatch(message)
    }
}
