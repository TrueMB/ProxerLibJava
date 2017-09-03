package me.proxer.library.entity.messenger;

import com.squareup.moshi.Json;
import lombok.Getter;
import lombok.Value;
import me.proxer.library.entity.ProxerDateItem;
import me.proxer.library.entity.ProxerIdItem;
import me.proxer.library.entity.ProxerImageItem;

import java.util.Date;

/**
 * Entity representing a single conference. This might be a group chat or a conversation with a
 * single user (indicated by {@link #isGroup()}.
 *
 * @author Ruben Gees
 */
@Value
public class Conference implements ProxerIdItem, ProxerDateItem, ProxerImageItem {

    /**
     * Returns the id.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "id")
    private String id;

    /**
     * Returns the topic.
     */
    @Json(name = "topic")
    private String topic;

    /**
     * Returns the custom topic.
     */
    @Json(name = "topic_custom")
    private String customTopic;

    /**
     * Returns the amount of participants.
     */
    @Json(name = "count")
    private int participantAmount;

    /**
     * Returns the id of the image.
     */
    @Getter(onMethod = @__({@Override}))
    private String image;

    /**
     * Returns the type of the image.
     */
    private String imageType;

    /**
     * Returns the group.
     */
    @Json(name = "group")
    private boolean group;

    /**
     * Returns true, if this conference has been read.
     */
    @Json(name = "read")
    private boolean read;

    /**
     * Returns the time.
     */
    @Getter(onMethod = @__({@Override}))
    @Json(name = "timestamp_end")
    private Date date;

    /**
     * Returns the amount of unread messages.
     */
    @Json(name = "read_count")
    private int unreadMessageAmount;

    /**
     * Returns the id of the last read message. Can be "0".
     */
    @Json(name = "read_mid")
    private String lastReadMessageId;
}