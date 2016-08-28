package com.proxerme.library.util;

import android.support.annotation.NonNull;
import android.support.annotation.RawRes;
import android.support.test.InstrumentationRegistry;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.HttpUrl;
import okio.Okio;

public class TestUtils {

    public static String loadResponse(@RawRes int resource) throws IOException {
        return Okio.buffer(Okio.source(InstrumentationRegistry.getContext().getResources()
                .openRawResource(resource))).readString(Charset.defaultCharset());
    }

    @NonNull
    public static HttpUrl buildHostUrl(@NonNull HttpUrl absoluteUrl) {
        return new HttpUrl.Builder()
                .scheme(absoluteUrl.scheme())
                .host(absoluteUrl.host())
                .port(absoluteUrl.port())
                .build();
    }
}