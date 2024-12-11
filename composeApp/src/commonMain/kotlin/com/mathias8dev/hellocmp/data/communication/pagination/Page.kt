package com.mathias8dev.hellocmp.data.communication.pagination

import dev.icerock.moko.parcelize.Parcelable
import dev.icerock.moko.parcelize.Parcelize
import kotlinx.serialization.Serializable


@Serializable
data class Page<T>(
    val content: List<T>,
    val pageable: Pageable,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean,
    val size: Int,
    val number: Int,
) :  Iterable<T> {
    override fun iterator(): Iterator<T> {
        return content.iterator()
    }

    operator fun plus(page: Page<T>): Page<T> {
        return this.copy(
            content = content + page.content,
            pageable = page.pageable,
            totalPages = page.totalPages,
            last = page.last,
            size = page.size,
            number = page.number
        )
    }
}

fun <T> Page<T>.isNotEmpty(): Boolean {
    return this.content.isNotEmpty()
}


fun <T> Page<T>.isEmpty(): Boolean {
    return this.content.isEmpty()
}


fun <T, R> Page<T>.map(transform: (T) -> R): Page<R> {
    return Page(
        content = this.content.map(transform),
        pageable = pageable,
        totalPages = totalPages,
        totalElements = totalElements,
        last = last,
        size = size,
        number = number
    )
}

fun <T> Page<T>.forEach(action: (it: T) -> Unit) {
    this.content.forEach(action)
}

fun <T> Page<T>.hasMorePages(): Boolean {
    return this.pageable.pageNumber + 1 < this.totalPages
}

fun <T> Page<T>.nextPage(): Int {
    return if (this.hasMorePages()) this.pageable.pageNumber + 1
    else this.pageable.pageNumber
}