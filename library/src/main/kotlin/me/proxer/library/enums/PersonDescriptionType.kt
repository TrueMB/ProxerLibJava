package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available person description Types.
 *
 * @author TrueMB
 */

@JsonClass(generateAdapter = false)
enum class PersonDescriptionType {

    @Json(name = "intro")
    INTRO,

    @Json(name = "biography")
    BIOGRAPHY,

    @Json(name = "awards")
    AWARDS,

    @Json(name = "trivia")
    TRIVIA,
}
