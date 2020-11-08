package com.thunderdogge.messaggio

import android.content.Context

sealed class MessageResource {
    abstract fun format(context: Context): String

    class Id(private val resId: Int) : MessageResource() {
        override fun format(context: Context): String {
            return context.getString(resId)
        }
    }

    class Text(private val text: String) : MessageResource() {
        override fun format(context: Context): String {
            return text
        }
    }

    class Format(private val resId: Int, private vararg val formatArgs: Any) : MessageResource() {
        override fun format(context: Context): String {
            return context.getString(resId, *formatArgs)
        }
    }
}
