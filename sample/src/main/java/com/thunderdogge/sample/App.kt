package com.thunderdogge.sample

import android.app.Application
import toothpick.Toothpick
import toothpick.configuration.Configuration

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        Toothpick.setConfiguration(Configuration.forDevelopment().preventMultipleRootScopes())
        Toothpick.openRootScope().installModules(AppModule)
    }
}
