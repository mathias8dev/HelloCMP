package com.mathias8dev.hellocmp.data.communication.filtering

import kotlinx.serialization.Serializable


@Serializable
enum class FilterFunction(
    val key: String
) {

    LESS_THAN("lessThan"),

    LESS_THAN_OR_EQUAL_TO("lessThanOrEqualTo"),

    GREATER_THAN("greaterThan"),

    GREATER_THAN_OR_EQUAL_TO("greaterThanOrEqualTo"),

    FUZZY("fuzzy"),

    EQUALS("equals"),

    LIKE("like"),

    MEMBER("member"),

    BETWEEN("between"), ;
}

