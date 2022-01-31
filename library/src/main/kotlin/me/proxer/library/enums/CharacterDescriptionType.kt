package me.proxer.library.enums

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available character description Types.
 *
 * @author TrueMB
 */

@JsonClass(generateAdapter = false)
enum class CharacterDescriptionType {

    @Json(name = "intro")
    INTRO,

    @Json(name = "appearance")
    APPEARANCE,

    @Json(name = "personality")
    PERSONALITY,

    @Json(name = "skills")
    SKILLS,

    @Json(name = "present")
    PRESENT,

    @Json(name = "past")
    PAST,

    @Json(name = "trivia")
    TRIVIA,
}
