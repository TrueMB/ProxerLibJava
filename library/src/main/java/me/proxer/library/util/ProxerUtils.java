package me.proxer.library.util;

import com.squareup.moshi.Json;
import lombok.experimental.UtilityClass;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.Iterator;

/**
 * Utility class for internal use.
 *
 * @author Ruben Gees
 */
@UtilityClass
public class ProxerUtils {

    /**
     * Returns the name of the passed enum instance (Using that specified in the {@code @Json} annotation).
     */
    @Nullable
    public final String getApiEnumName(final Enum<?> it) {
        try {
            return it.getClass().getField(it.name()).getAnnotation(Json.class).name();
        } catch (NoSuchFieldException ignored) {
            return null;
        }
    }

    /**
     * Converts the passed {@code value} string to an instance of the passed enum {@code type}.
     * <p>
     * If the conversion is not possible, because the passed string was invalid, null is returned.
     * If an invalid enum type is passed (one that is not in this library), an exception is thrown.
     */
    @Nullable
    public final <T extends Enum<T>> T toApiEnum(final Class<T> type, final String value) {
        for (final Field field : type.getFields()) {
            if (field.getAnnotation(Json.class).name().equals(value)) {
                return Enum.valueOf(type, field.getName());
            }
        }

        return null;
    }

    /**
     * Joins an Iterator with a given delimiter to a {@link String} and returns the result.
     * <p>
     * The Iterator can have any type, toString is called on the individual items.
     */
    public final String join(final String delimiter, final Iterable<?> iterable) {
        final Iterator<?> iterator = iterable.iterator();

        if (!iterator.hasNext()) {
            return "";
        }

        final StringBuilder builder = new StringBuilder().append(iterator.next());

        while (iterator.hasNext()) {
            builder.append(delimiter).append(iterator.next());
        }

        return builder.toString();
    }
}
