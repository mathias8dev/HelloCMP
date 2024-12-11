package com.mathias8dev.hellocmp.data.communication.filtering

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable



@Serializable
data class FilterCriteria(
    val id: String,
    val filterFn: String,
    val value: @Contextual Any
)



