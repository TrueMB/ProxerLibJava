package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Language
import me.proxer.library.enums.OccupationType

/**
 * Entity with basic Information from a Person.
 *
 * @property name The name.
 * @property occupation The occupation
 * @property language The language
 *
 * @author TrueMB
 */
@JsonClass(generateAdapter = true)
data class PersonCore(
    @Json(name = "pid") override val id: String,
    @Json(name = "type") val occupation: OccupationType,
    @Json(name = "language") val language: Language? = null,
    @Json(name = "name") val name: String
) : ProxerIdItem
