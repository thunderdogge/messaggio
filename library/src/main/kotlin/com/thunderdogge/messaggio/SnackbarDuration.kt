package com.thunderdogge.messaggio

import android.content.Context
import com.google.android.material.snackbar.Snackbar

enum class SnackbarDuration : IMessageDuration {
    Short,
    Long,
    Indefinite;

    override fun get(context: Context): Int {
        return when (this) {
            Short -> Snackbar.LENGTH_SHORT
            Long -> Snackbar.LENGTH_LONG
            Indefinite -> Snackbar.LENGTH_INDEFINITE
        }
    }
}
