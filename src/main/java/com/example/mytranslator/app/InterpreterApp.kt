package com.example.mytranslator.app

import android.app.Application
import com.example.mytranslator.di.application
import com.example.mytranslator.di.historyScreen
import com.example.mytranslator.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class InterpreterApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}