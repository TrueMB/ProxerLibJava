package com.proxerme.library.api.messenger;

import com.proxerme.library.api.Endpoint;
import com.proxerme.library.api.ProxerCall;
import lombok.experimental.Accessors;
import org.jetbrains.annotations.NotNull;

/**
 * Endpoint for creating a new conference with a single other user.
 * <p>
 * Upon success, the result is a single string with the id of the newly created conference.
 *
 * @author Ruben Gees
 */
@Accessors(fluent = true)
public final class CreateConferenceEndpoint implements Endpoint {

    private final InternalApi internalApi;

    private final String firstMessage;
    private final String username;

    CreateConferenceEndpoint(@NotNull final InternalApi internalApi, @NotNull final String firstMessage,
                             @NotNull final String username) {
        this.internalApi = internalApi;
        this.firstMessage = firstMessage;
        this.username = username;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @NotNull
    public ProxerCall<String> build() {
        return internalApi.createConference(firstMessage, username);
    }
}
