package com.ulb.common.net

data class HttpResult<T>(
    var code: String = "10001",
    var msg: String = "",
    var success: Boolean = false,
    var data: T
)

