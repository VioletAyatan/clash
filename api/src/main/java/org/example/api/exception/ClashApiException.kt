package org.example.api.exception

import org.example.api.pojo.ClashErrorResponse

/**
 * 代表调用ClashApi出现错误
 */
class ClashApiException(
    message: String,
    val detailMessage: String,
) : RuntimeException(message) {

    constructor(clashErrorResponse: ClashErrorResponse) : this(
        clashErrorResponse.reason.plus(""),
        clashErrorResponse.message
    )

}