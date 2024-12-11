package com.mathias8dev.hellocmp

import android.app.Application
import com.mathias8dev.hellocmp.injection.initKoin

class HelloCmpApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //ApplicationModule().module
        debugBuild()
        initKoin()
    }
}