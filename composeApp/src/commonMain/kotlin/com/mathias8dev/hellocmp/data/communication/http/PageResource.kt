package com.mathias8dev.hellocmp.data.communication.http

import androidx.compose.runtime.Composable
import com.mathias8dev.hellocmp.data.communication.pagination.Page
import com.mathias8dev.hellocmp.data.communication.pagination.hasMorePages
import com.mathias8dev.hellocmp.data.communication.pagination.isEmpty
import com.mathias8dev.hellocmp.data.communication.pagination.isNotEmpty
import com.mathias8dev.hellocmp.data.communication.pagination.nextPage
import com.mathias8dev.hellocmp.domain.dtos.PageableDto

sealed class PageResource<T> {

    fun isSuccess(): Boolean = this is Success

    fun isIdle(): Boolean = this is Idle

    fun isLoading(): Boolean = this is Loading

    fun isPartialLoading(): Boolean = this is Loading && this.previousData != null

    fun isError(): Boolean = this is Error

    fun hasData(): Boolean = (this.isLoading() || this.isError() || this.isSuccess()) && (
            (this as? Loading)?.previousData != null ||
                    (this as? Error)?.previousData != null ||
                    (this as? Success)?.data != null
            )

    fun retrieveData(): Page<T>? = (this as? Success)?.data ?: (this as? Loading)?.previousData
    ?: (this as? Error)?.previousData


    class Idle<T> : PageResource<T>()

    class Loading<T>(
        val previousData: Page<T>? = null
    ) : PageResource<T>()

    class Error<T>(
        val message: String? = null,
        val cause: Throwable? = null,
        val previousData: Page<T>? = null,
    ) : PageResource<T>()

    class Success<T>(
        val data: Page<T>
    ) : PageResource<T>()
}


inline fun <T> PageResource<T>.onEmpty(callback: () -> Unit) {
    if (this is PageResource.Success<T> && this.data.isEmpty()) {
        callback()
    }
}


inline fun <T> PageResource<T>.onNotEmpty(callback: (data: Page<T>) -> Unit) {
    if (this is PageResource.Success<T> && this.data.isNotEmpty()) {
        callback(data)
    }
}


inline fun <T> PageResource<T>.onLoading(callback: (previousData: Page<T>?) -> Unit) {
    if (this is PageResource.Loading<T>) {
        callback(previousData)
    }
}


inline fun <T> PageResource<T>.onError(callback: (message: String?, cause: Throwable?, previousData: Page<T>?) -> Unit) {
    if (this is PageResource.Error<T>) {
        callback(message, cause, previousData)
    }
}


inline fun <T> PageResource<T>.onData(callback: (data: Page<T>) -> Unit) {

    this.onError { message, cause, previousData ->
        previousData?.let {
            callback(it)
        }
    }

    this.onLoading { data ->
        data?.let {
            callback(it)
        }
    }

    this.onSuccess {
        callback(it)
    }
}


inline fun <T> PageResource<T>.onSuccess(callback: (data: Page<T>) -> Unit) {
    if (this is PageResource.Success<T>) {
        callback(data)
    }
}


fun <T> PageResource<T>.nextPageable(
    pageableDto: PageableDto,
    nextPage: Boolean
): PageableDto {
    return if (nextPage && this is PageResource.Success && this.data.hasMorePages()) {
        pageableDto.copy(page = this.data.nextPage())
    } else pageableDto
}


fun <T> Page<T>?.nextPageable(
    pageableDto: PageableDto,
    nextPage: Boolean,
    reset: Boolean = false
): PageableDto {
    return if (reset) pageableDto.copy(page = 0)
    else if (nextPage && this?.hasMorePages() == true) {
        pageableDto.copy(page = this.nextPage())
    } else pageableDto
}


@Composable
fun <T> BuildPageResourceComposable(
    resource: PageResource<T>,
    onLoading: @Composable ((previousData: Page<T>?) -> Unit)? = null,
    onError: @Composable ((message: String?, cause: Throwable?, previousData: Page<T>?) -> Unit)? = null,
    onSuccess: @Composable ((data: Page<T>) -> Unit)? = null,
    onData: @Composable ((data: Page<T>) -> Unit)? = null,
) {
    resource.onError { message, cause, previousData ->
        onError?.invoke(message, cause, previousData)
    }

    resource.onLoading {
        onLoading?.invoke(it)
    }

    resource.onSuccess {
        onSuccess?.invoke(it)
    }

    resource.onData {
        onData?.invoke(it)
    }
}