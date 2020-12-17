package com.slavainc.myapplication.core

import android.app.Application
import com.slavainc.myapplication.core.di.KoinInjector

class ApolloApp : Application() {

    override fun onCreate() {
        super.onCreate()
        KoinInjector.setup(this)
    }
}