package me.proxer.library.api.anime;

import lombok.Setter;
import lombok.experimental.Accessors;
import me.proxer.library.api.Endpoint;
import me.proxer.library.api.ProxerCall;
import me.proxer.library.entity.anime.Stream;
import me.proxer.library.enums.AnimeLanguage;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Endpoint for retrieving the streams of the passed entry, concerning the episode and language.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class StreamsEndpoint implements Endpoint<List<Stream>> {

    private final InternalApi internalApi;

    private final String id;
    private final int episode;
    private final AnimeLanguage language;

    /**
     * Sets if the Proxer streams should be included in the result.
     * <p>
     * Note, that those require higher access levels.
     */
    @Nullable
    @Setter
    private Boolean includeProxerStreams;

    StreamsEndpoint(final InternalApi internalApi, final String id, final int episode, final AnimeLanguage language) {
        this.internalApi = internalApi;
        this.id = id;
        this.episode = episode;
        this.language = language;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProxerCall<List<Stream>> build() {
        if (includeProxerStreams == null || !includeProxerStreams) {
            return internalApi.streams(id, episode, language);
        } else {
            return internalApi.proxerStreams(id, episode, language);
        }
    }
}
