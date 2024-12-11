package com.mathias8dev.hellocmp.domain.dtos

import com.mathias8dev.hellocmp.data.communication.filtering.Sorting
import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Parcelize
@Serializable
data class PageableDto(
    val page: Int,
    val pageSize: Int,
    val sorting: List<Sorting>
) : Parcelable {

    companion object {
        const val defaultPageSize = 20
        const val defaultPage = 0
        fun default(): PageableDto {
            return PageableDto(
                page = defaultPage,
                pageSize = defaultPageSize,
                sorting = listOf(Sorting.default())
            )
        }
    }
}