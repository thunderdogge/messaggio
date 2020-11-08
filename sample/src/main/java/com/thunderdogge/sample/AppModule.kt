package com.thunderdogge.sample

import com.thunderdogge.messaggio.MessageDispatcher
import com.thunderdogge.messaggio.Messaggio
import com.thunderdogge.messaggio.Messenger
import toothpick.config.Module

object AppModule : Module() {
    init {
        val messaggio = Messaggio.create()
        bind(Messenger::class.java).toInstance(messaggio.messenger)
        bind(MessageDispatcher::class.java).toInstance(messaggio.dispatcher)
    }
}
