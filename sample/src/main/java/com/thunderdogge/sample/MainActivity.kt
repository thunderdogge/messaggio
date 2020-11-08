package com.thunderdogge.sample

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.thunderdogge.messaggio.MessageDispatcher
import toothpick.Toothpick

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val scope by lazy(LazyThreadSafetyMode.NONE) {
        Toothpick.openRootScope()
    }

    private val messageReceiver by lazy(LazyThreadSafetyMode.NONE) {
        MainMessageReceiver(this)
    }

    private val messageDispatcher by lazy(LazyThreadSafetyMode.NONE) {
        scope.getInstance(MessageDispatcher::class.java)
    }

    private val viewModel by lazy(LazyThreadSafetyMode.NONE) {
        val factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return scope.getInstance(modelClass)
            }
        }

        ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(R.id.toastButton).setOnClickListener { viewModel.showToast() }
        findViewById<View>(R.id.snackbarButton).setOnClickListener { viewModel.showSnackbar() }
        findViewById<View>(R.id.customMessageButton).setOnClickListener { viewModel.showCustomMessage() }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        messageDispatcher.attachReceiver(messageReceiver)
    }

    override fun onPause() {
        messageDispatcher.detachReceiver()

        super.onPause()
    }
}
