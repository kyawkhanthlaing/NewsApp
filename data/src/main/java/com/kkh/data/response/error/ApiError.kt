package com.kkh.data.response.error

const val GENERIC_ERROR_MSG = "Oops! Something went wrong."
const val CONNECTION_ERROR_MSG = "Your are in the offline. Try to reconnect!"
const val SERVER_ERROR_MSG = "Internal Server Error! Something went wrong!."
const val BAD_REQUEST_MSG = "Sorry! We don't understand your request!"
const val NOT_FOUND_MSG = "Sorry! We can't find exactly what you're looking for."

sealed class ApiError(msg: String) : Throwable(msg) {
    data class ConnectionError(override val message: String = CONNECTION_ERROR_MSG) :
        ApiError(message)

    data class BadRequestError(override val message: String = BAD_REQUEST_MSG) : ApiError(message)
    data class SeverError(override val message: String = SERVER_ERROR_MSG) : ApiError(message)
    data class NotFoundError(override val message: String = NOT_FOUND_MSG) : ApiError(message)
    data class GenericError(override val message: String = GENERIC_ERROR_MSG) : ApiError(message)
}

