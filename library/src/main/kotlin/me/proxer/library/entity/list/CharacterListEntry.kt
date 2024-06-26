package me.proxer.library.entity.list

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.CharacterType

/**
 * Entity holding all relevant info about a single entry of the character list.
 *
 * @property name The name.
 * @property description The description.
 * @property type The character type.
 *
 * @author TrueMB
 */
@JsonClass(generateAdapter = true)
data class CharacterListEntry(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "type") val type: CharacterType? = null,
    @Json(name = "text") val description: String? = null

) : ProxerIdItem
