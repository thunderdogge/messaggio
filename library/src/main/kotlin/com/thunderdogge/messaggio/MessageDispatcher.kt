package com.thunderdogge.messaggio

class MessageDispatcher {
    private var receiver: IMessageReceiver? = null

    private val messageBuffer = mutableListOf<IMessage>()

    fun dispatch(message: IMessage) {
        if (receiver == null) {
            messageBuffer.add(message)
        } else {
            requireNotNull(receiver).onMessageReceived(message)
        }
    }

    fun attachReceiver(receiver: IMessageReceiver) {
        if (messageBuffer.any()) {
            messageBuffer.forEach(receiver::onMessageReceived)
            messageBuffer.clear()
        }

        this.receiver = receiver
    }

    fun detachReceiver() {
        receiver = null
    }
}
