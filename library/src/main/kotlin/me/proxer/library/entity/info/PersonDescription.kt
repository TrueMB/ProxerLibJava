package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.enums.Language
import me.proxer.library.enums.PersonDescriptionType

/**
 * Entity holding the description Info from [Character].
 *
 * @property PersonDescriptionType The description type
 * @property text The text to the description
 * @property language The language in which the text was written
 */
@JsonClass(generateAdapter = true)
data class PersonDescription(
    @Json(name = "subject") val characterDescriptionType: PersonDescriptionType,
    @Json(name = "text") val text: String,
    @Json(name = "language") val language: Language
)
