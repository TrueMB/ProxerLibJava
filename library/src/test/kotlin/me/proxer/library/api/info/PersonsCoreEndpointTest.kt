package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.PersonCore
import me.proxer.library.enums.OccupationType
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

/**
 * @author TrueMB
 */
class PersonsCoreEndpointTest : ProxerTest() {

    private val firstExpectedEntry = PersonCore(
        id = "1272",
        occupation = OccupationType.MISC,
        language = null,
        name = "Justin Cook"
    )

    private val secondExpectedEntry = PersonCore(
        id = "1974",
        occupation = OccupationType.DIRECTOR,
        language = null,
        name = "Seiji Mizushima"
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("persons.json") {
            api.info
                .persons("1")
                .build()
                .safeExecute()
        }

        result.first() shouldBeEqualTo firstExpectedEntry
        result[1] shouldBeEqualTo secondExpectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("persons.json") {
            api.info.persons("1")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/persons?id=1"
    }
}
