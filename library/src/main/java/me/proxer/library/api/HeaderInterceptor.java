package me.proxer.library.api;

import me.proxer.library.util.ProxerUrls;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import javax.annotation.Nullable;
import java.io.IOException;

/**
 * @author Ruben Gees
 */
final class HeaderInterceptor implements Interceptor {

    private static final String API_KEY_HEADER = "proxer-api-key";
    private static final String USER_AGENT_HEADER = "User-Agent";

    private final String apiKey;
    private final String userAgent;

    HeaderInterceptor(final String apiKey, @Nullable final String userAgent) {
        this.apiKey = apiKey;
        this.userAgent = userAgent;
    }

    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request oldRequest = chain.request();

        if (ProxerUrls.hasProxerHost(oldRequest.url())) {
            final Request.Builder newRequestBuilder = chain.request().newBuilder();

            if (oldRequest.url().host().equals(ProxerUrls.apiBase().host())) {
                newRequestBuilder.header(API_KEY_HEADER, apiKey);
            }

            if (userAgent != null) {
                newRequestBuilder.header(USER_AGENT_HEADER, userAgent);
            }

            return chain.proceed(newRequestBuilder.build());
        } else {
            throw new IllegalArgumentException("Only use ProxerLib's OkHttp instance with Proxer.Me URLs!");
        }
    }
}
