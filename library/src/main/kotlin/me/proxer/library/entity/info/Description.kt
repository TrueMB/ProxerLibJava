package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.enums.DescriptionType
import me.proxer.library.enums.Language

/**
 * Entity holding the description Info from [Character].
 *
 * @property descriptionType The description type
 * @property text The text to the description
 * @property language The language in which the text was written
 */
@JsonClass(generateAdapter = true)
data class Description(
    @Json(name = "subject") val descriptionType: DescriptionType,
    @Json(name = "text") val text: String,
    @Json(name = "language") val language: Language
)
