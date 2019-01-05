package me.proxer.library.api;

import me.proxer.library.ProxerTest;
import me.proxer.library.api.ProxerException.ErrorType;
import me.proxer.library.api.ProxerException.ServerErrorType;
import me.proxer.library.entity.notifications.NewsArticle;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.SocketPolicy;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Ruben Gees
 */
class ProxerCallTest extends ProxerTest {

    @Test
    void testTimeoutError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json"))
                .setSocketPolicy(SocketPolicy.NO_RESPONSE));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.TIMEOUT,
                        "Exception should have the TIMEOUT ErrorType");
    }

    @Test
    void testIOError() {
        server.enqueue(new MockResponse().setResponseCode(404));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.IO,
                        "Exception should have the IO ErrorType")
                .matches(exception -> "Unsuccessful request: 404".equals(exception.getMessage()),
                        "Exception should have message with HTTP error code");
    }

    @Test
    void testIOErrorWithBody() {
        server.enqueue(new MockResponse().setBody("error!").setResponseCode(404));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.IO,
                        "Exception should have the IO ErrorType")
                .matches(exception -> "Unsuccessful request: 404".equals(exception.getMessage()),
                        "Exception should have message with HTTP error code");
    }

    @Test
    void testInvalidEncodingError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json").replace(":", "invalid")));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.IO,
                        "Exception should have the IO ErrorType");
    }

    @Test
    void testInvalidDataError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json").replace("256", "invalid")));

        assertThatExceptionOfType(ProxerException.class).isThrownBy(() -> api.notifications().news().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.PARSING,
                        "Exception should have the PARSING ErrorType");
    }

    @Test
    void testServerError() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("conferences_error.json")));

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(() -> api.messenger().conferences().build().execute())
                .matches(exception -> exception.getErrorType() == ErrorType.SERVER,
                        "Exception should have the SERVER ErrorType")
                .matches(exception -> exception.getServerErrorType() == ServerErrorType.MESSAGES_LOGIN_REQUIRED,
                        "Exception should have the MESSAGES_LOGIN_REQUIRED ServerErrorType")
                .matches(exception -> exception.getMessage() != null
                                && exception.getMessage().equals("Du bist nicht eingeloggt."),
                        "Exception should have the correct message");
    }

    @Test
    void testServerErrorWithMessage() throws IOException {
        server.enqueue(new MockResponse().setBody(fromResource("ucp_settings_error.json")));

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(() -> api.ucp().setSettings().build().execute())
                .matches(exception -> exception.getMessage() != null
                                && exception.getMessage().equals("Ungültige Eingabe für Felder.\n[profil]"),
                        "Exception should have the correct message");
    }

    @SuppressWarnings("unchecked")
    @Test
    void testUnknownError() throws Exception {
        final Call<ProxerResponse<?>> internalCall = mock(Call.class);
        final ProxerCall<?> call = new ProxerCall(internalCall);

        when(internalCall.execute()).thenThrow(IllegalStateException.class);

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(call::execute)
                .matches(exception -> exception.getErrorType() == ErrorType.UNKNOWN,
                        "Exception should have the UNKNOWN ErrorType")
                .withCauseExactlyInstanceOf(IllegalStateException.class);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testSafeExecute() throws IOException, ProxerException {
        final Call<ProxerResponse<List<NewsArticle>>> internalCall = mock(Call.class);
        final ProxerCall<List<NewsArticle>> call = new ProxerCall<>(internalCall);
        final Response<ProxerResponse<List<NewsArticle>>> internalResponse = mock(Response.class);
        final ProxerResponse<List<NewsArticle>> response = mock(ProxerResponse.class);

        when(internalResponse.isSuccessful()).thenReturn(true);
        when(response.isSuccessful()).thenReturn(true);
        when(internalResponse.body()).thenReturn(response);
        when(response.getData()).thenReturn(Collections.emptyList());
        when(internalCall.execute()).thenReturn(internalResponse);

        assertThat(call.safeExecute()).isNotNull();
    }

    @SuppressWarnings("unchecked")
    @Test
    void testSafeExecuteNull() throws IOException {
        final Call<ProxerResponse<List<NewsArticle>>> internalCall = mock(Call.class);
        final ProxerCall<List<NewsArticle>> call = new ProxerCall<>(internalCall);
        final Response<ProxerResponse<List<NewsArticle>>> internalResponse = mock(Response.class);
        final ProxerResponse<List<NewsArticle>> response = mock(ProxerResponse.class);

        when(internalResponse.isSuccessful()).thenReturn(true);
        when(response.isSuccessful()).thenReturn(true);
        when(internalResponse.body()).thenReturn(response);
        when(response.getData()).thenReturn(null);
        when(internalCall.execute()).thenReturn(internalResponse);

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(call::safeExecute)
                .matches(exception -> exception.getErrorType() == ErrorType.UNKNOWN,
                        "Exception should have the UNKNOWN ErrorType")
                .withCauseExactlyInstanceOf(NullPointerException.class);
    }

    @Test
    void testEnqueue() throws IOException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        api.notifications().news().build().enqueue(result -> lock.countDown(),
                exception -> {
                    // Failed. The lock will never be counted down and timeout.
                });


        assertTimeout(Duration.ofSeconds(1000), () -> lock.await());
    }

    @Test
    void testEnqueueError() throws IOException, InterruptedException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")).setResponseCode(404));

        api.notifications().news().build().enqueue(result -> {
                    // Failed. The lock will never be counted down and timeout.
                },
                exception -> {
                    if (exception.getErrorType() == ErrorType.IO) {
                        lock.countDown();
                    }

                    // Failed: Not the exception we want. The lock will never be counted down and timeout.
                });

        lock.await();
    }

    @Test
    void testIsExecuted() throws IOException, ProxerException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.execute();

        assertThat(call.isExecuted()).isTrue();
    }

    @Test
    void testCancel() throws IOException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.enqueue(result -> {
            // Failed. The lock will never be counted down and timeout.
        }, exception -> {
            assertThat(exception.getErrorType()).isEqualTo(ErrorType.CANCELLED);

            lock.countDown();
        });

        call.cancel();
        assertTimeout(Duration.ofSeconds(1000), () -> lock.await());
    }

    @Test
    void testIsCanceled() throws IOException {
        final CountDownLatch lock = new CountDownLatch(1);

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.enqueue(result -> {
            // Failed. The lock will never be counted down and timeout.
        }, exception -> lock.countDown());

        call.cancel();
        assertTimeout(Duration.ofSeconds(1000), () -> lock.await());

        assertThat(call.isCanceled()).isTrue();
    }

    @Test
    void testClone() throws ProxerException, IOException {
        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        final ProxerCall<List<NewsArticle>> call = api.notifications().news().build();

        call.execute();

        server.enqueue(new MockResponse().setBody(fromResource("news.json")));

        assertThat(call.clone().execute()).isNotNull();
    }

    @Test
    void testRequest() {
        assertThat(api.notifications().news().build().request()).isNotNull();
    }

    @SuppressWarnings("unchecked")
    @Test
    void testRequestThrowingErrorWithoutMessage() throws IOException {
        final Call<ProxerResponse<NewsArticle>> mockedCall = mock(Call.class);
        final ProxerCall<NewsArticle> call = new ProxerCall<>(mockedCall);

        when(mockedCall.execute()).thenThrow(new IOException());

        assertThatExceptionOfType(ProxerException.class)
                .isThrownBy(call::execute)
                .withCauseExactlyInstanceOf(IOException.class)
                .matches(it -> it.getErrorType() == ErrorType.IO);
    }
}
