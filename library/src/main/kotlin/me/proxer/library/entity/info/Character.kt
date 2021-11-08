package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Gender
import java.util.Date

/**
 * Entity holding all info of a character.
 *
 * @property name The name.
 * @property gender The gender
 * @property hairColor The hair color.
 * @property eyeColor The eye color.
 * @property bloodType The BloodType.
 * @property birthday The birthday.
 * @property height The height.
 * @property weight The weight.
 * @property descriptions The description.
 * @property persons The persons in contact with the character
 *
 * @author Ruben Gees
 */
@JsonClass(generateAdapter = true)
data class Character(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "gender") val gender: Gender,
    @Json(name = "hair_color") val hairColor: String,
    @Json(name = "eye_color") val eyeColor: String,
    @Json(name = "bloodtype") val bloodType: String,
    @Json(name = "birthday") val birthday: Date,
    @Json(name = "height") val height: String,
    @Json(name = "weight") val weight: String,
    @Json(name = "description") val descriptions: Set<Description>,
    @Json(name = "persons") val persons: Set<PersonCore>
) : ProxerIdItem
