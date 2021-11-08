package me.proxer.library.api.list

import me.proxer.library.ProxerTest
import me.proxer.library.entity.list.CharacterListEntry
import me.proxer.library.enums.DescriptionType
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author TrueMB
 */
class CharacterSearchEndpointTest : ProxerTest() {

    private val expectedEntry = CharacterListEntry(
        id = "108",
        name = "Asuna Yuuki",
        description = "[b]Asuna Yuuki[/b] ist die Freundin von [url=http://proxer.me/character?id=43#top][i]" +
            "Kirito[/i][/url] und bildet gemeinsam mit ihm den Kopf der Gilde [i]Ritter des Blutschwurs[/i]. " +
            "Es ist eine mittelgroße Gilde von etwa dreißig Spielern, die auch als die stärkste Gilde in " +
            "[i]Aincrad[/i] bekannt ist. Sie ist eine erfahrene Spielerin, welche den Titel [i]Flash[/i] " +
            "für ihre außergewöhnlichen schnellen Schwertfertigkeiten verdient hat."
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("character_list_entry.json") {
            api.list
                .characterSearch()
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo expectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("character_list_entry.json") {
            api.list.characterSearch()
                .start("Asu")
                .contains("na")
                .search("hellbraunes")
                .subject(DescriptionType.APPEARANCE)
                .limit(10)
                .page(3)
                .build()
                .execute()
        }

        request.path shouldBeEqualTo """
                /api/v1/list/characters?start=Asu&contains=na&search=hellbraunes&subject=appearance&page=3&limit=10
        """.trimIndent().replace("\n", "")
    }
}
