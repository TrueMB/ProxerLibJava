package me.proxer.library.enums

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Json

/**
 * Enum holding the sub types of a [me.proxer.library.entity.list.Tag].
 *
 * @author Ruben Gees
 */
@FallbackEnum(name = "OTHER")
enum class TagSubType {

    @Json(name = "misc")
    MISCELLANEOUS,

    @Json(name = "persoenlichkeiten")
    PERSONALITIES,

    @Json(name = "gefuehle")
    FEELINGS,

    @Json(name = "zeichnung")
    DRAWING,

    @Json(name = "uebernatuerliches")
    SUPERNATURAL,

    @Json(name = "sport")
    SPORT,

    @Json(name = "menschen")
    PEOPLE,

    @Json(name = "zukunft")
    FUTURE,

    @Json(name = "story")
    STORY,

    @Json(name = "prota")
    PROTAGONIST,

    @Json(name = "")
    OTHER
}
