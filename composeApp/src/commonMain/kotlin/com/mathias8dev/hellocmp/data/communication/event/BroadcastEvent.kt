package com.mathias8dev.hellocmp.data.communication.event


sealed class BroadcastEvent : Event() {

    data class HideNavigationBar(val shouldBeHidden: Boolean = false) : BroadcastEvent()

    data object RedirectToSignIn : BroadcastEvent()
}