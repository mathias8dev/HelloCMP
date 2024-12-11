package com.mathias8dev.hellocmp

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier


fun debugBuild() {
    Napier.base(DebugAntilog())
}