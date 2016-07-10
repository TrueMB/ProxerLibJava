package com.proxerme.library.connection;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.afollestad.bridge.BridgeException;
import com.proxerme.library.connection.ProxerException.ErrorCode;

import org.json.JSONException;

import static com.proxerme.library.connection.ProxerException.IO;
import static com.proxerme.library.connection.ProxerException.NETWORK;
import static com.proxerme.library.connection.ProxerException.TIMEOUT;
import static com.proxerme.library.connection.ProxerException.UNKNOWN;
import static com.proxerme.library.connection.ProxerException.UNPARSEABLE;

/**
 * A Helper class, which converts an Exception to a Integer, represented through the
 * {@link ErrorCode} annotation. It also has
 * a method to convert an ErrorCode into a human readable String.
 *
 * @author Ruben Gees
 */
class ProxerErrorHandler {

    /**
     * Handles a JSONException and returns the appropriate {@link ProxerException}.
     *
     * @param jsonException The Exception.
     * @return The appropriate {@link ProxerException}.
     */
    @SuppressWarnings("UnusedParameters")
    @NonNull
    @CheckResult
    static ProxerException handleException(@NonNull JSONException jsonException) {
        return new ProxerException(UNPARSEABLE);
    }

    /**
     * Handles a BridgeException and returns the appropriate {@link ProxerException}.
     *
     * @param bridgeException The Exception.
     * @return The appropriate {@link ProxerException}.
     */
    @NonNull
    @CheckResult
    static ProxerException handleException(@NonNull BridgeException bridgeException) {
        ProxerException exception;

        switch (bridgeException.reason()) {
            case BridgeException.REASON_REQUEST_TIMEOUT: {
                exception = new ProxerException(TIMEOUT);

                break;
            }

            case BridgeException.REASON_RESPONSE_UNSUCCESSFUL: {
                exception = new ProxerException(NETWORK);

                break;
            }

            case BridgeException.REASON_RESPONSE_UNPARSEABLE: {
                exception = new ProxerException(UNPARSEABLE);

                break;
            }

            case BridgeException.REASON_RESPONSE_IOERROR: {
                exception = new ProxerException(IO);

                break;
            }

            case BridgeException.REASON_RESPONSE_VALIDATOR_FALSE: {
                exception = new ProxerException(UNKNOWN);

                break;
            }

            case BridgeException.REASON_RESPONSE_VALIDATOR_ERROR: {
                exception = (ProxerException) bridgeException.underlyingException();

                break;
            }

            case BridgeException.REASON_REQUEST_CANCELLED: {
                exception = new ProxerException(UNKNOWN);

                break;
            }

            case BridgeException.REASON_REQUEST_FAILED: {
                exception = new ProxerException(NETWORK);

                break;
            }

            default: {
                exception = new ProxerException(UNKNOWN);

                break;
            }
        }

        //noinspection ConstantConditions
        return exception;
    }

}
