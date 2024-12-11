package com.mathias8dev.hellocmp.data.communication.filtering

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
class Sorting(
    val key: String,
    val direction: Direction,
) : Parcelable {

    @Parcelize
    enum class Direction : Parcelable {
        ASC,
        DESC
    }

    companion object {
        fun default(): Sorting = Sorting("createdAt", Direction.DESC)
    }
}