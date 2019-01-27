package me.proxer.library.api

import com.squareup.moshi.Json

/**
 * @author Ruben Gees
 */
internal class ProxerResponse<T>(
    @field:NumberBasedBoolean @Json(name = "error") val error: Boolean,
    @Json(name = "message") val message: String,
    @Json(name = "code") val code: Int,
    @Json(name = "data") val data: T?
) {

    val isSuccessful get() = !error
}
