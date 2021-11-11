package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.PersonListEntry
import me.proxer.library.enums.PersonDescriptionType
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author TrueMB
 */
class PersonSearchEndpointTest : ProxerTest() {

    private val expectedTag = PersonListEntry(
        id = "2224",
        name = "Kotomi Aoki",
        description = """
            |[B]Kotomi Aoki[/B] ist eine japanische Mangaka, die man vor allem für
            | [B][I][URL=https://proxer.me/info/9175#top]Boku wa Imouto ni Koi wo Suru[/URL][/I][/B] und
            | [B][I][URL=https://proxer.me/info/9385#top]Kanojo wa Uso wo Aishisugiteru[/URL][/I][/B] kennt.
            """.trimMargin().replace("\n", "")
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("person_list_entry.json") {
            api.list
                .personSearch()
                .build()
                .safeExecute()
        }

        result[2] shouldBeEqualTo expectedTag
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("person_list_entry.json") {
            api.list.personSearch()
                .searchStart("Koto")
                .contains("Aoki")
                .search("japanische")
                .subject(PersonDescriptionType.INTRO)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo """
                /api/v1/list/persons?start=Koto&contains=Aoki&search=japanische&subject=intro
        """.trimIndent()
    }
}
