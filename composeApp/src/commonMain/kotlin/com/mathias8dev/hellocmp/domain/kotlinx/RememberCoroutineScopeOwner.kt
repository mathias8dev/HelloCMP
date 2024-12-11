package com.mathias8dev.hellocmp.domain.kotlinx

import androidx.compose.runtime.RememberObserver
import com.mathias8dev.hellocmp.domain.kotlinx.CoroutineScopeOwner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.cancel


class RememberCoroutineScopeOwner(dispatcher: CoroutineDispatcher) : CoroutineScopeOwner(dispatcher), RememberObserver {
    override fun onAbandoned() {
        coroutineScope.cancel()
    }

    override fun onForgotten() {
        coroutineScope.cancel()
    }

    override fun onRemembered() {}
}