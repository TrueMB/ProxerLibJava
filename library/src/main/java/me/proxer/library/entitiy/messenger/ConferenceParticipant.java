package me.proxer.library.entitiy.messenger;

import com.squareup.moshi.Json;
import lombok.Value;
import me.proxer.library.entitiy.ProxerIdItem;
import me.proxer.library.entitiy.ProxerImageItem;

/**
 * Entity that represents a participant in a {@link Conference}.
 *
 * @author Desnoo
 */
@Value
public class ConferenceParticipant implements ProxerIdItem, ProxerImageItem {

    /**
     * {@inheritDoc}
     */
    @Json(name = "uid")
    private String id;

    /**
     * {@inheritDoc}
     */
    @Json(name = "avatar")
    private String image;

    /**
     * Returns the username.
     */
    @Json(name = "username")
    private String username;

    /**
     * Returns the current status.
     */
    @Json(name = "status")
    private String status;
}
