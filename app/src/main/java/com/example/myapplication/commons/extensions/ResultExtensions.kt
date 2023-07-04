@file:Suppress("TooGenericExceptionCaught", "RethrowCaughtException")

package com.example.myapplication.commons.extensions

import kotlinx.coroutines.CancellationException

/**
 * Like [runCatching], but with proper coroutines cancellation handling.
 * Also only catches [Exception] instead of [Throwable].
 *
 * Cancellation exceptions need to be rethrown. See https://github.com/Kotlin/kotlinx.coroutines/issues/1814.
 */
inline fun <R> resultOf(block: () -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}

/**
 * Like [runCatching], but with proper coroutines cancellation handling.
 * Also only catches [Exception] instead of [Throwable].
 *
 * Cancellation exceptions need to be rethrown. See https://github.com/Kotlin/kotlinx.coroutines/issues/1814.
 */
inline fun <T, R> T.resultOf(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException) {
        throw e
    } catch (e: Exception) {
        Result.failure(e)
    }
}

/**
 * Like [mapCatching], but uses [resultOf] instead of [runCatching].
 */
inline fun <R, T> Result<T>.mapResult(transform: (value: T) -> R): Result<R> {
    val successResult = getOrNull()
    return when {
        successResult != null -> resultOf { transform(successResult) }
        else -> Result.failure(exceptionOrNull() ?: error("Unreachable state"))
    }
}

/**
 * Like [recoverCatching], but uses [resultOf] instead of [runCatching].
 */
inline fun <R, T : R> Result<T>.recoverResult(transform: (exception: Throwable) -> R): Result<R> {
    return when (val exception = exceptionOrNull()) {
        null -> this
        else -> resultOf { transform(exception) }
    }
}
