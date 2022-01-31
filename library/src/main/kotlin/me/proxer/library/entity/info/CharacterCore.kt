package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Language
import me.proxer.library.enums.OccupationType

/**
 * Entity with basic Information from a Character.
 *
 * @property name The name.
 * @property type The type
 * @property language The language
 *
 * @author TrueMB
 */
@JsonClass(generateAdapter = true)
data class CharacterCore(
    @Json(name = "cid") override val id: String,
    @Json(name = "type") val type: OccupationType,
    @Json(name = "language") val language: Language,
    @Json(name = "name") val name: String
) : ProxerIdItem
