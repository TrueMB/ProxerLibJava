package me.proxer.library.entity.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem

/**
 * Entity holding all relevant info about a single entry of the person list.
 *
 * @property name The name.
 * @property description The description.
 *
 * @author TrueMB
 */
@JsonClass(generateAdapter = true)
data class PersonListEntry(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "text") val description: String
) : ProxerIdItem
