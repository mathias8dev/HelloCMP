package com.mathias8dev.hellocmp.injection

import com.mathias8dev.hellocmp.injection.modules.ApplicationModule
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

fun initKoin() {
    startKoin {
        modules(
            ApplicationModule().module
        )
    }
}