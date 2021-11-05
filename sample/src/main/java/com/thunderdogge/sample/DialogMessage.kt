package com.thunderdogge.sample

import com.thunderdogge.messaggio.IMessage
import com.thunderdogge.messaggio.IMessenger

class DialogMessage(
    val title: CharSequence,
    val text: CharSequence
) : IMessage

fun IMessenger.showDialog(title: CharSequence, text: CharSequence) {
    val message = DialogMessage(title, text)
    postMessage(message)
}
