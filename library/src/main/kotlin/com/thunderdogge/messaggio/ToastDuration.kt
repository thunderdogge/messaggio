package com.thunderdogge.messaggio

import android.content.Context
import android.widget.Toast

enum class ToastDuration : IMessageDuration {
    Short,
    Long;

    override fun get(context: Context): Int {
        return when (this) {
            Short -> Toast.LENGTH_SHORT
            Long -> Toast.LENGTH_LONG
        }
    }
}
