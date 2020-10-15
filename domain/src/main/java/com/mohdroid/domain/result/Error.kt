package com.mohdroid.domain.result

import com.mohdroid.domain.enums.ErrorType


data class Error(
    val type: ErrorType,
    var message: String = "")