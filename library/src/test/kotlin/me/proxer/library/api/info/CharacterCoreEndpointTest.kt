package me.proxer.library.api.info

import me.proxer.library.ProxerTest
import me.proxer.library.entity.info.*
import me.proxer.library.enums.*
import me.proxer.library.runRequest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test
import java.util.*

/**
 * @author TrueMB
 */
class CharacterCoreEndpointTest : ProxerTest() {

    private val expectedCharacter = Character(
        id = "1",
        name = "Test Charakter",
        gender = Gender.UNKNOWN,
        hairColor = "#ff1b1b",
        eyeColor = "#313eff",
        bloodType = "o",
        birthday = Date(709164000000L),
        height = "176",
        weight = "79",
        description = setOf(Description(
                descriptionType = DescriptionType.INTRO,
                text = "Lorenor Zorro ist ein Pirat aus der Serie One Piece. Er ist Mitglied der Strohhutbande " +
                    "und der erste Kämpfer der von [I]Monkey D. Ruffy[/I] rekrutiert wurde. Sein Ziel ist es, " +
                    "der beste Schwertkämpfer der Welt zu werden. Dieser Charakter ist zum Testen der Funktionen dar.",
                Language.GERMAN),

            Description(
                descriptionType = DescriptionType.APPEARANCE,
                text = "Zorro hat kurze grüne Haare über die er in Kämpfen ein schwarzes Kopftuch trägt, " +
                    "welches er ansonsten um den Arm gewickelt hat. Meistens trägt er weisses T-Shirt und eine " +
                    "schwarze Hose. Er hat immer eine gruene Bauchbinde umgewickelt mit denen er seine drei Schwerter " +
                    "befestigt. Ausserdem hat er drei goldene Ohrringe in seinem linken Ohr. Er hat eine grosse Narbe " +
                    "quer über die Brust und zwei weitere um seine Knöchel die er sich selbst zugefügt hat. " +
                    "Nach dem Zeitsprung hat er ausserdem eine Narbe über dem linken Auge.",
                Language.GERMAN),

            Description(
                descriptionType = DescriptionType.PERSONALITY,
                text = "Seine Ehre ist ihm extrem wichtig, so würde er nie von einem Kampf davonlaufen " +
                    "oder um Gnade bitten. Allerdings kann er seinen Stolz auch herunterschlucken, " +
                    "was er zum Beispiel tat als er Falkenauge bat ihn als Schüler anzunehmen. Generell ist Zorro " +
                    "ein sehr selbstbewusster Mensch. Er streitet sich sehr oft mit Sanji.",
                Language.GERMAN),

            Description(
                descriptionType = DescriptionType.SKILLS,
                text = "Zorro ist ein sehr fähiger Schwertkämpfer der mit drei Schwertern kämpft. " +
                    "Er kann sogar Stein und Eisen zerschneiden. Seine Hiebe sind so schnell, " +
                    "dass die getroffene Stelle anfängt zu brennen. Ausserdem kann er mit dem Sinnesphoenix " +
                    "auch aus der Entfernung angreifen, da er die Luft schneiden kann welche dann den Gegner trifft. " +
                    "Mit Asura kann er Kopien von sich selbst erzeugen, diese sind jedoch nur Illusionen und " +
                    "er kann nur zwei auf einmal bilden. Desweitern hat er enorme physische Kräfte und " +
                    "eine beachtliche Regenerationskraft. Ausserdem besitzt er das Haki.",
                Language.GERMAN),

            Description(
                descriptionType = DescriptionType.PAST,
                text = "Zorro ist in dem Dorf Shimotsuki im East Blue aufgewachsen wo er in einem " +
                    "Dojo Kendo erlernte. Kuina die Tochter des Lehrers war die stärkste ihm Dojo weswegen Zorro " +
                    "immer wieder von ihr besiegt wurde. Nach einem Kampf mit echten Schwertern, den Zorro " +
                    "ebenfalls verliert, beschließen beide alles dafür zu geben der beste im Schwertkampf zu werden. " +
                    "Eines Tages stirbt Kuina jedoch nach einem Sturz von der Treppe. Daraufhin bekommt Zorro " +
                    "das Schwert von Kuina und ist jetzt umso entschlossener sein Ziel zu erreichen. " +
                    "In den folgenden neun Jahren perfektioniert er seinen Drei-Schwert-Stil und macht sich " +
                    "dann auf die Suche nach Falkenauge, wobei er sich mit Kopfgeldjägertätigkeiten über Wasser" +
                    " hält.",
                Language.GERMAN),

            Description(
                descriptionType = DescriptionType.TRIVIA,
                text = "Zorro Geburtsdatum, der 11.11. ist ein Zahlenpalindrom, da er von vorne nach hinten " +
                    "und von hinten nach vorne gelesen das Gleiche ergibt. Ausserdem ist das japanische Wort " +
                    "für Palindrom Zorome dessen Kurzform Zoro ist.",
                Language.GERMAN)
        ),
        persons = setOf(PersonCore(id = "140", occupation = OccupationType.SEIYUU, Language.OTHER, name = "Test Person"))
    )

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("character.json") {
            api.info
                .character("1")
                .build()
                .execute()
        }

        result shouldBeEqualTo expectedCharacter
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("character.json") {
            api.info.character("1")
                .build()
                .execute()
        }

        request.path shouldBeEqualTo "/api/v1/info/character?id=1"
    }
}
