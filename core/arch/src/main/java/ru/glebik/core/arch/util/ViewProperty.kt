package ru.glebik.core.arch.util

import ru.glebik.core.arch.MviViewModel

sealed interface ViewProperty<out R> {
    data class Content<out T>(val content: T) : ViewProperty<T>
    data object Loading : ViewProperty<Nothing>
    data class Error(val errorMessage: String, val error: Throwable? = null) : ViewProperty<Nothing>

    val isLoading
        get() = this is Loading

    val isContent
        get() = this is Content

    val isError
        get() = this is Error
}

val <R> ViewProperty<R>.safeContent: R?
    get() = (this as? ViewProperty.Content)?.content

val <R> ViewProperty<R>.unsafeContent: R
    get() = requireNotNull((this as? ViewProperty.Content)?.content)

fun MviViewModel<*, *, *>.loading() = ViewProperty.Loading

fun <T> MviViewModel<*, *, *>.content(content: T) = ViewProperty.Content(content)

fun MviViewModel<*, *, *>.failure(errorMessage: String, error: Throwable? = null) =
    ViewProperty.Error(errorMessage, error)
