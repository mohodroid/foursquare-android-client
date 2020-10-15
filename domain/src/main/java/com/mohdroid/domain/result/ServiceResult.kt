package com.mohdroid.domain.result

/**
 * A generic class that holds a value
 * @param <T>
 */
sealed class ServiceResult<out T> {

    data class Success<out T>(val data: T?) : ServiceResult<T>()
    data class Failure(val error: Error) : ServiceResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[exception=$error]"
        }
    }
}



