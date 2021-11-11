package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available character types.
 *
 * @author TrueMB
 */

@JsonClass(generateAdapter = false)
@FallbackEnum(name = "UNKNOWN")
enum class CharacterType {

    @Json(name = "main")
    MAIN,

    @Json(name = "support")
    SUPPORT,

    @Json(name = "")
    UNKNOWN,
}
