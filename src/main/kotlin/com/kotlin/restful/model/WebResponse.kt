package com.kotlin.restful.model

/**
 * ini sebagai response genericnya
 */
data class WebResponse<T>(
    val code: Int,

    val status: String,

    val data: T
)
