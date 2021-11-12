package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.CharacterCore
import me.proxer.library.entity.info.Occupation
import me.proxer.library.entity.info.Person
import me.proxer.library.entity.info.PersonDescription
import me.proxer.library.enums.Gender
import me.proxer.library.enums.Language
import me.proxer.library.enums.OccupationType
import me.proxer.library.enums.PersonDescriptionType
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.Date

/**
 * @author TrueMB
 */
class PersonEndpointTest : ProxerTest() {

    private val expectedEntry = Person(
        id = "133",
        name = "Haruka Tomatsu",
        gender = Gender.FEMALE,
        bloodType = "B",
        birthday = Date(634086000000L),
        birthplace = "Ichinomiya / Japan",
        nationality = "JP",
        residence = "Tokyo / Japan",
        descriptions = setOf(
            PersonDescription(
                PersonDescriptionType.INTRO,
                text = """
                   |[b]Haruka Tomatsu[/b] ist eine japanische Synchronsprecherin und Sängerin. Sie ist am
                   | 4. Februar 1990 in der Präfektur Aichi in Japan geboren worden und steht bei der Talentagentur
                   | [i]Music Ray'n Inc[/i] unter Vertrag. Musikalisch gesehen beschäftigt sich Tomatsu hauptsächlich
                   | mit Songs rund um Anime. Abseits davon produziert sie allerdings auch Songs und Alben.
                    """.trimMargin().replace("\n", ""),
                Language.GERMAN
            ),

            PersonDescription(
                PersonDescriptionType.BIOGRAPHY,
                text = """
                    |2005 nahm sie an der [i]1. Music Ray’n Super Seiyū Audition[/i] teil, welchen sie letztlich
                    | auch gewann und damit direkt unter Vertrag genommen wurde. Tomatsu hatte ihr Debüt als
                    | Synchronsprecherin im Anime [url=http://proxer.me/info/618#top][b][i]Shinkyoku Soukai
                    | Polyphonica[/i][/b][/url] als [i]Corticarte Apa Lagranges[/i]. Seitdem hatte sie andere
                    | Hauptrollen wie [i]Lala Satalin Deviluke[/i] in [url=http://proxer.me/info/91#top][b]
                    |[i]To LOVE-Ru[/i][/b][/url], [i]Shiho Sannomiya[/i] in [url=http://proxer.me/info/1174#top][b]
                    |[i]Zettai Karen Children[/i][/b][/url], [i]M.M[/i] in [url=http://proxer.me/info/1012#top][b]
                    |[i]Kemeko Deluxe![/i][/b][/url] und [i]Nagi[/i] in [url=http://proxer.me/info/391#top]
                    |[b][i]Kannagi[/i][/b][/url].Ihr Hochschulstudium in Tokio begann sie im April 2008.
                    | Am 15. Februar 2009 gründete sie zusammen mit [url=https://proxer.me/persons?id=819#top]
                    |[i]Ayahi Takagaki[/i][/url], [url=https://proxer.me/persons?id=26#top][i]Minako Kotobuki[/i][/url]
                    | und [url=https://proxer.me/persons?id=275#top][i]Aki Toyosaki[/i][/url]
                    | eine Musikgruppe, welche sie den
                    | Namen [URL=https://proxer.me/interprets?id=214#top][i]Sphere[/i][/URL] gaben.
                    """.trimMargin().replace("\n", ""),
                Language.GERMAN
            ),

            PersonDescription(
                PersonDescriptionType.AWARDS,
                text = """
                    |Sie erhielt neben [url=https://proxer.me/persons?id=130#top][i]Kana Asumi[/i][/url]
                    | als [I]Beste Nachwuchssprecherin [/I]für die Rollen [i]Nagi[/i]
                    | aus [url=http://proxer.me/info/391#top][b][i]Kannagi[/i][/b][/url], [i]Lala Satalin Deviluke[/i]
                    | aus [url=http://proxer.me/info/91#top][b][i]To LOVE-Ru[/i][/b][/url] und [i]Shiho Sannomiya[/i]
                    | aus [url=http://proxer.me/info/1174#top][b][i]Zettai Karen Children[/i][/b][/url] den 3.
                    | [i]Seiyū Award[/i] 2009.
                    """.trimMargin().replace("\n", ""),
                Language.GERMAN
            )
        ),
        occupations = setOf(
            Occupation(OccupationType.MUSICIAN),
            Occupation(OccupationType.SEIUU)
        ),
        characters = setOf(
            CharacterCore(
                id = "108",
                type = OccupationType.SEIYUU,
                language = Language.OTHER,
                name = "Asuna Yuuki"
            ),

            CharacterCore(
                id = "327",
                type = OccupationType.SEIYUU,
                language = Language.OTHER,
                name = "Haruka Takayama"
            ),

            CharacterCore(
                id = "393",
                type = OccupationType.SEIYUU,
                language = Language.OTHER,
                name = "Morgiana"
            )
        )
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("person.json") {
            api.info
                .person("133")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedEntry
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("person.json") {
            api.info.person("133")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/person?id=133"
    }
}
