package org.example.api.pojo

data class ClashErrorResponse(
    val reason: String,
    val message: String,
    val type: String,
    val detail: Any,
)
