package me.proxer.library.api

import com.serjltt.moshi.adapters.FallbackEnum
import com.squareup.moshi.Moshi
import me.proxer.library.BuildConfig
import me.proxer.library.api.ProxerApi.Builder
import me.proxer.library.api.anime.AnimeApi
import me.proxer.library.api.apps.AppsApi
import me.proxer.library.api.chat.ChatApi
import me.proxer.library.api.forum.ForumApi
import me.proxer.library.api.info.InfoApi
import me.proxer.library.api.list.ListApi
import me.proxer.library.api.manga.MangaApi
import me.proxer.library.api.media.MediaApi
import me.proxer.library.api.messenger.MessengerApi
import me.proxer.library.api.notifications.NotificationsApi
import me.proxer.library.api.ucp.UcpApi
import me.proxer.library.api.user.UserApi
import me.proxer.library.util.ProxerUrls
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Entry point for access to the various APIs of Proxer.
 *
 * Before usage, you have to construct an instance through the [Builder].
 *
 * Note, that this is not a singleton. Each instance is separated from each other; You could even have instances with a
 * different api key.
 *
 * @author Ruben Gees
 */
class ProxerApi private constructor(retrofit: Retrofit) {

    private companion object {
        private val CERTIFICATES = arrayOf(
            "sha256/58qRu/uxh4gFezqAcERupSkRYBlBAvfcw7mEjGPLnNU=",
            "sha256/grX4Ta9HpZx6tSHkmCrvpApTQGo67CYDnvprLg5yRME=",
            "sha256/Vjs8r4z+80wjNcr1YKepWQboSIRi63WsWXhIMN+eWys="
        )

        private const val DEFAULT_USER_AGENT = "ProxerLibJava/" + BuildConfig.VERSION
    }

    /**
     * The respective API.
     */
    val notifications = NotificationsApi(retrofit)
        @JvmName("notifications") get

    /**
     * The respective API.
     */
    val user = UserApi(retrofit)
        @JvmName("user") get

    /**
     * The respective API.
     */
    val info = InfoApi(retrofit)
        @JvmName("info") get

    /**
     * The respective API.
     */
    val messenger = MessengerApi(retrofit)
        @JvmName("messenger") get

    /**
     * The respective API.
     */
    val chat = ChatApi(retrofit)
        @JvmName("chat") get

    /**
     * The respective API.
     */
    val list = ListApi(retrofit)
        @JvmName("list") get

    /**
     * The respective API.
     */
    val ucp = UcpApi(retrofit)
        @JvmName("ucp") get

    /**
     * The respective API.
     */
    val anime = AnimeApi(retrofit)
        @JvmName("anime") get

    /**
     * The respective API.
     */
    val manga = MangaApi(retrofit)
        @JvmName("manga") get

    /**
     * The respective API.
     */
    val forum = ForumApi(retrofit)
        @JvmName("forum") get

    /**
     * The respective API.
     */
    val media = MediaApi(retrofit)
        @JvmName("media") get

    /**
     * The respective API.
     */
    val apps = AppsApi(retrofit)
        @JvmName("apps") get

    /**
     *
     * You can set customized instances of the internally used libraries: Moshi, OkHttp and Retrofit.
     * Moreover you can specify your own [LoginTokenManager] and user agent.
     *
     * @constructor Constructs a new instance of the builder, with the passed [apiKey].
     */
    class Builder(private val apiKey: String) {

        private var loginTokenManager: LoginTokenManager? = null
        private var userAgent: String? = null
        private var moshi: Moshi? = null
        private var client: OkHttpClient? = null
        private var retrofit: Retrofit? = null

        /**
         * Sets a custom login token manager.
         */
        fun loginTokenManager(loginTokenManager: LoginTokenManager) = this.apply {
            this.loginTokenManager = loginTokenManager
        }

        /**
         * Sets a custom user agent.
         *
         * If not set, it will default to "ProxerLibJava/<version>"
         */
        fun userAgent(userAgent: String) = this.apply { this.userAgent = userAgent }

        /**
         * Sets a custom Moshi instance.
         *
         * Note that a internally a new instance will be constructed with the adjustments included you did on your
         * instance.
         */
        fun moshi(moshi: Moshi) = this.apply { this.moshi = moshi }

        /**
         * Sets a custom OkHttp instance.
         *
         * Note that internally a new instance will be constructed with the adjustments included you did on your
         * instance.
         */
        fun client(client: OkHttpClient) = this.apply { this.client = client }

        /**
         * Sets a custom Retrofit instance.
         *
         * Note that a internally a new instance will be constructed with the adjustments included you did on your
         * instance.
         */
        fun retrofit(retrofit: Retrofit) = this.apply { this.retrofit = retrofit }

        /**
         * Finally builds the [ProxerApi] with the provided adjustments.
         */
        fun build(): ProxerApi {
            return ProxerApi(buildRetrofit())
        }

        private fun buildRetrofit(): Retrofit {
            return (retrofit?.newBuilder() ?: Retrofit.Builder())
                .baseUrl(ProxerUrls.apiBase)
                .client(buildClient())
                .addCallAdapterFactory(ProxerResponseCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(buildMoshi()))
                .addConverterFactory(EnumRetrofitConverterFactory())
                .build()
        }

        private fun buildClient(): OkHttpClient {
            return (client?.newBuilder() ?: OkHttpClient.Builder())
                .apply {
                    interceptors().apply {
                        addAll(
                            0, listOf(
                                HeaderInterceptor(apiKey, buildUserAgent()),
                                LoginTokenInterceptor(buildLoginTokenManager()),
                                HttpsEnforcingInterceptor()
                            )
                        )
                    }

                    certificatePinner(constructCertificatePinner())
                }
                .build()
        }

        private fun buildLoginTokenManager(): LoginTokenManager {
            return loginTokenManager ?: DefaultLoginTokenManager()
        }

        private fun buildUserAgent(): String {
            return userAgent ?: DEFAULT_USER_AGENT
        }

        private fun constructCertificatePinner(): CertificatePinner {
            return CertificatePinner.Builder()
                .apply { CERTIFICATES.forEach { add(ProxerUrls.webBase.host(), it) } }
                .build()
        }

        private fun buildMoshi(): Moshi {
            return (moshi?.newBuilder() ?: Moshi.Builder())
                .add(UnitAdapter())
                .add(DateAdapter())
                .add(PageAdapter())
                .add(HttpUrlAdapter())
                .add(ConferenceAdapter())
                .add(EpisodeInfoAdapter())
                .add(AdaptionInfoAdapter())
                .add(NotificationAdapter())
                .add(ConferenceInfoAdapter())
                .add(BooleanAdapterFactory())
                .add(NotificationInfoAdapter())
                .add(FixRatingDetailsAdapter())
                .add(UcpSettingConstraintAdapter())
                .add(DelimitedEnumSetAdapterFactory())
                .add(DelimitedStringSetAdapterFactory())
                .add(FallbackEnum.ADAPTER_FACTORY)
                .build()
        }
    }
}
