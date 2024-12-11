package com.mathias8dev.hellocmp.data.communication.filtering

import kotlinx.serialization.Serializable


@Serializable
enum class FilterMode(val key: String) {


    AND("and"),
    OR("or"),
    AND_MANUAL("andManual"),
    OR_MANUAL("orManual");

    val isAnd: Boolean
        get() = this == AND

    val isOr: Boolean
        get() = this == OR

    val isAndManual: Boolean
        get() = this == AND_MANUAL

    val isOrManual: Boolean
        get() = this == OR_MANUAL
}