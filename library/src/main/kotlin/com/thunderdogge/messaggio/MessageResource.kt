package com.thunderdogge.messaggio

import android.content.Context
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class MessageResource : IMessageResource {
    class Id(@StringRes private val resId: Int) : MessageResource() {
        override fun format(context: Context): CharSequence {
            return context.getString(resId)
        }
    }

    class Raw(private val text: CharSequence) : MessageResource() {
        override fun format(context: Context): CharSequence {
            return text
        }
    }

    class Text(@StringRes private val resId: Int) : MessageResource() {
        override fun format(context: Context): CharSequence {
            return context.getText(resId)
        }
    }

    class Format(@StringRes private val resId: Int, private vararg val formatArgs: Any) : MessageResource() {
        override fun format(context: Context): CharSequence {
            return context.getString(resId, *formatArgs)
        }
    }

    class Quantity(@PluralsRes private val resId: Int, private val quantity: Int, private vararg val formatArgs: Any) : MessageResource() {
        override fun format(context: Context): CharSequence {
            return context.resources.getQuantityString(resId, quantity, *formatArgs)
        }
    }
}
