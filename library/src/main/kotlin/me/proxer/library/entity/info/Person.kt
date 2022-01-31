package me.proxer.library.entity.info

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import me.proxer.library.entity.ProxerIdItem
import me.proxer.library.enums.Gender
import java.util.Date

/**
 * Entity with basic Information from a Person.
 *
 * @property name The name.
 * @property gender The gender
 * @property bloodType The BloodType.
 * @property birthday The birthday.
 * @property birthplace The birthplace.
 * @property nationality The nationality.
 * @property residence The residence.
 * @property descriptions The descriptions.
 * @property occupations The occupations.
 * @property characters The characters in contact with the person
 *
 * @author TrueMB
 */
@JsonClass(generateAdapter = true)
data class Person(
    @Json(name = "id") override val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "gender") val gender: Gender,
    @Json(name = "bloodtype") val bloodType: String,
    @Json(name = "birthday") val birthday: Date,
    @Json(name = "birthplace") val birthplace: String,
    @Json(name = "nationality") val nationality: String,
    @Json(name = "residence") val residence: String,
    @Json(name = "description") val descriptions: Set<PersonDescription>,
    @Json(name = "occupations") val occupations: Set<Occupation>,
    @Json(name = "characters") val characters: Set<CharacterCore>
) : ProxerIdItem
