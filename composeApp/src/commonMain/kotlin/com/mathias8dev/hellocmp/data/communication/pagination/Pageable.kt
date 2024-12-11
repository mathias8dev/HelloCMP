package com.mathias8dev.hellocmp.data.communication.pagination

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class Pageable(
    val pageNumber: Int,
    val pageSize: Int
) : Parcelable