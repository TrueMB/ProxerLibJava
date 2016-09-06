package com.proxerme.library.parameters;

import android.support.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class containing the available states of a
 * {@link com.proxerme.library.connection.list.entity.MediaListEntry}.
 *
 * @author Ruben Gees
 */
public final class StateParameter {

    public static final int PRE_AIRING = 0;
    public static final int FINISHED = 1;
    public static final int AIRING = 2;
    public static final int CANCELLED = 3;
    public static final int CANCELLED_SUB = 4;

    private StateParameter() {
    }

    /**
     * An annotation that represents the possible season.
     */
    @IntDef({PRE_AIRING, FINISHED, AIRING, CANCELLED, CANCELLED_SUB})
    @Retention(value = RetentionPolicy.SOURCE)
    @Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
    public @interface State {
    }

}
