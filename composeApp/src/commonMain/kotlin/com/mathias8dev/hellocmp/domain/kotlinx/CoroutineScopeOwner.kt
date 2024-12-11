package com.mathias8dev.hellocmp.domain.kotlinx


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel


interface CoroutineScopeProvider : Closeable {
    val coroutineScope: CoroutineScope
}

open class CoroutineScopeOwner(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : CoroutineScopeProvider {
    private val supervisorJob = SupervisorJob()
    private var scope: CloseableCoroutineScope? = CloseableCoroutineScope(dispatcher + supervisorJob)

    override val coroutineScope: CoroutineScope
        get() {
            val newScope = CloseableCoroutineScope(dispatcher + supervisorJob)
            if (scope == null) {
                scope = newScope
            }
            return newScope
        }

    override fun close() {
        scope?.cancel()
        scope = null
    }
}

