package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Enum holding the available occupation of a person
 *
 * @author TrueMB
 */
@JsonClass(generateAdapter = false)
@FallbackEnum(name = "UNKNOWN")
enum class OccupationType {

    @Json(name = "author")
    AUTHOR,

    @Json(name = "art")
    ART,

    @Json(name = "author-art")
    AUTHOR_ART,

    @Json(name = "seiuu")
    SEIUU,

    @Json(name = "seiyuu")
    SEIYUU,

    @Json(name = "original-creator")
    ORIGINAL_CREATOR,

    @Json(name = "director")
    DIRECTOR,

    @Json(name = "mangaka")
    MANGAKA,

    @Json(name = "musician")
    MUSICIAN,

    @Json(name = "misc")
    MISC,

    @Json(name = "")
    UNKNOWN,
}
