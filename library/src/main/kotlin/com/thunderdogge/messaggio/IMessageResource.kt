package com.thunderdogge.messaggio

import android.content.Context

interface IMessageResource {
    fun format(context: Context): CharSequence
}
