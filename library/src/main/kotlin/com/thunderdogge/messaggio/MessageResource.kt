package com.thunderdogge.messaggio

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class MessageResource {
    abstract fun format(context: Context): String

    class Id(@StringRes private val resId: Int) : MessageResource() {
        override fun format(context: Context): String {
            return context.getString(resId)
        }
    }

    class Text(private val text: String) : MessageResource() {
        override fun format(context: Context): String {
            return text
        }
    }

    class Format(@StringRes private val resId: Int, private vararg val formatArgs: Any) : MessageResource() {
        override fun format(context: Context): String {
            return context.getString(resId, *formatArgs)
        }
    }

    class Quantity(@PluralsRes private val resId: Int, private val quantity: Int, private vararg val formatArgs: Any) : MessageResource() {
        override fun format(context: Context): String {
            return context.resources.getQuantityString(resId, quantity, *formatArgs)
        }
    }
}
