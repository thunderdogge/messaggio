package com.thunderdogge.messaggio

class Messaggio(
    val messenger: Messenger,
    val dispatcher: MessageDispatcher
) {
    companion object {
        fun create(): Messaggio {
            val dispatcher = MessageDispatcher()
            return Messaggio(Messenger(dispatcher), dispatcher)
        }
    }
}
