package me.proxer.library.api.info;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException;
import me.proxer.library.entity.info.AdaptionInfo;
import me.proxer.library.entity.info.Entry;
import me.proxer.library.entity.info.EntrySeasonInfo;
import me.proxer.library.entity.info.EntryTranslatorGroup;
import me.proxer.library.entity.info.InfoTag;
import me.proxer.library.entity.info.Synonym;
import me.proxer.library.entity.list.IndustryCore;
import me.proxer.library.enums.Category;
import me.proxer.library.enums.Country;
import me.proxer.library.enums.FskConstraint;
import me.proxer.library.enums.Genre;
import me.proxer.library.enums.IndustryType;
import me.proxer.library.enums.License;
import me.proxer.library.enums.MediaLanguage;
import me.proxer.library.enums.MediaState;
import me.proxer.library.enums.Medium;
import me.proxer.library.enums.Season;
import me.proxer.library.enums.SynonymType;
import okhttp3.mockwebserver.MockResponse;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Ruben Gees
 */
public class EntryEndpointTest extends ProxerTest {

    @Test
    public void testDefault() throws IOException, ProxerException, ParseException {
        server.enqueue(new MockResponse().setBody(fromResource("entry.json")));

        final Entry result = api.info()
                .entry("1")
                .build()
                .execute();

        assertThat(result).isEqualTo(buildTestEntry());
    }

    @Test
    public void testPath() throws ProxerException, IOException, InterruptedException {
        server.enqueue(new MockResponse().setBody(fromResource("entry.json")));

        api.info().entry("1")
                .build()
                .execute();

        assertThat(server.takeRequest().getPath()).isEqualTo("/api/v1/info/fullentry?id=1");
    }

    private Entry buildTestEntry() throws ParseException {
        return new Entry("6174", "LuCu LuCu",
                EnumSet.of(Genre.COMEDY, Genre.FANTASY, Genre.SEINEN, Genre.SLICE_OF_LIFE),
                EnumSet.noneOf(FskConstraint.class),
                "Humans are a despicable lot, committing sin after sin, filling the endless boundaries "
                        + "of the underworld with tortured souls. Now, it would seem, Hell isn't so endless after all, "
                        + "and it has become dangerously close to filling, and then overflowing into the human realm. "
                        + "Princess Lucuha sees this imminent disaster and has a plan: save Hell by making humans "
                        + "decent again. Of course, the angels can't simply allow demons to roam freely on Earth, "
                        + "and they do their best to stop Lucu and her dastardly plans.",
                Medium.MANGASERIES, 90, MediaState.FINISHED, 7, 1, 134,
                Category.MANGA, License.NOT_LICENSED, new AdaptionInfo("2793", "KissXsis", Medium.MANGASERIES),
                false, Arrays.asList(
                new Synonym("12322", "6174", SynonymType.ORIGINAL, "LuCu LuCu"),
                new Synonym("12323", "6174", SynonymType.JAPANESE, "るくるく"),
                new Synonym("44662", "6174", SynonymType.ORIGINAL_ALTERNATIVE, "Lucu Lucu"),
                new Synonym("44663", "6174", SynonymType.ORIGINAL_ALTERNATIVE, "LuCuLuCu")
        ), Collections.singleton(MediaLanguage.ENGLISH), Arrays.asList(
                new EntrySeasonInfo("1061", 2002, Season.UNSPECIFIED),
                new EntrySeasonInfo("15776", 2009, Season.UNSPECIFIED)
        ), Arrays.asList(
                new EntryTranslatorGroup("215", "SnoopyCool", Country.ENGLAND),
                new EntryTranslatorGroup("294", "FoOlRulez", Country.ENGLAND)
        ), Collections.singletonList(
                new IndustryCore("19", "Kodansha", IndustryType.PUBLISHER, Country.JAPAN)
        ), Arrays.asList(
                new InfoTag("93", "2027",
                        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2016-06-18 14:14:22"), false,
                        false, "Dämonen", "In diesem Werk kommen Dämonen vor."),
                new InfoTag("299", "2028",
                        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("2016-06-18 14:14:30"), false,
                        false, "Slapstick", "Situationskomik, kommt ohne Worte aus.")
        ));
    }
}
