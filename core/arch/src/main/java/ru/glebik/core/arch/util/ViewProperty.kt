package ru.glebik.core.arch.util

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

inline fun <R> ViewProperty<R>.requireContent(action: (R) -> Unit) {
    safeContent?.let {
        action(it)
    }
}

val <R> ViewProperty<R>.safeContent: R?
    get() = (this as? ViewProperty.Content)?.content

val <R> ViewProperty<R>.unsafeContent: R
    get() = requireNotNull((this as? ViewProperty.Content)?.content)

fun loading() = ViewProperty.Loading

fun <T> content(content: T) = ViewProperty.Content(content)

fun failure(errorMessage: String, error: Throwable? = null) =
    ViewProperty.Error(errorMessage, error)
