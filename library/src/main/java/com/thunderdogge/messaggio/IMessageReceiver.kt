package com.thunderdogge.messaggio

interface IMessageReceiver {
    fun onMessageReceived(message: IMessage)
}
