package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.enums.OccupationType

/**
 * Entity holding the data an occupation
 *
 * @property type The type
 *
 * @author TrueMB
 */
@JsonClass(generateAdapter = true)
data class Occupation(
    @Json(name = "type") val type: OccupationType,
)
