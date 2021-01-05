package com.thunderdogge.messaggio

open class Messenger(private val dispatcher: MessageDispatcher) : IMessenger {
    fun showToast(text: CharSequence, duration: IMessageDuration = ToastDuration.Long) {
        showToast(MessageResource.Raw(text), duration)
    }

    fun showToast(resId: Int, duration: IMessageDuration = ToastDuration.Long) {
        showToast(MessageResource.Id(resId), duration)
    }

    fun showToast(resId: Int, vararg values: Any, duration: IMessageDuration = ToastDuration.Long) {
        showToast(MessageResource.Format(resId, *values), duration)
    }

    fun showToast(resource: IMessageResource, duration: IMessageDuration = ToastDuration.Long) {
        val message = ToastMessage(resource, duration)
        postMessage(message)
    }

    fun showSnackbar(text: CharSequence, duration: IMessageDuration = SnackbarDuration.Long) {
        showSnackbar(MessageResource.Raw(text), duration)
    }

    fun showSnackbar(resId: Int, duration: IMessageDuration = SnackbarDuration.Long) {
        showSnackbar(MessageResource.Id(resId), duration)
    }

    fun showSnackbar(resId: Int, vararg values: Any, duration: IMessageDuration = SnackbarDuration.Long) {
        showSnackbar(MessageResource.Format(resId, *values), duration)
    }

    fun showSnackbar(resource: IMessageResource, duration: IMessageDuration = SnackbarDuration.Long) {
        val message = SnackbarMessage(resource, duration)
        postMessage(message)
    }

    override fun postMessage(message: IMessage) {
        dispatcher.dispatch(message)
    }
}
