package me.proxer.library.api.anime

import me.proxer.library.ProxerCall
import me.proxer.library.entity.anime.LinkContainer
import me.proxer.library.entity.anime.Stream
import me.proxer.library.enums.AnimeLanguage
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Ruben Gees
 */
@Suppress("UndocumentedPublicFunction")
internal interface InternalApi {

    @GET("anime/streams")
    fun streams(
        @Query("id") id: String?,
        @Query("episode") episode: Int?,
        @Query("language") language: AnimeLanguage?
    ): ProxerCall<List<Stream>>

    @GET("anime/proxerstreams")
    fun proxerStreams(
        @Query("id") id: String?,
        @Query("episode") episode: Int?,
        @Query("language") language: AnimeLanguage?
    ): ProxerCall<List<Stream>>

    @GET("anime/link2")
    fun link(
        @Query("id") id: String?,
        @Query("adFlag") adFlag: Int?
    ): ProxerCall<LinkContainer>
}
