package ru.glebik.core.arch.util

sealed interface ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>
    open class Failure(
        val exception: Throwable? = null,
        val code: Int? = null,
    ): ResultWrapper<Nothing>
    data object Loading : ResultWrapper<Nothing>
}