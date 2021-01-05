package com.thunderdogge.sample

import androidx.lifecycle.ViewModel
import com.thunderdogge.messaggio.Messenger
import com.thunderdogge.messaggio.SnackbarDuration
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val messenger: Messenger
) : ViewModel() {
    fun showToast() {
        messenger.showToast("Hello from toast!")
    }

    fun showSnackbar() {
        messenger.showSnackbar("Hello from snackbar!", SnackbarDuration.Short)
    }

    fun showCustomMessage() {
        messenger.showDialog("Message", "Hello from dialog!")
    }
}
