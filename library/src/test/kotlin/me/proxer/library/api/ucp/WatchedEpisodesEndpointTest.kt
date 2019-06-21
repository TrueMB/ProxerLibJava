package me.proxer.library.api.ucp

import me.proxer.library.ProxerTest
import me.proxer.library.runRequest
import org.amshove.kluent.shouldEqual
import org.junit.jupiter.api.Test

/**
 * @author Ruben Gees
 */
class WatchedEpisodesEndpointTest : ProxerTest() {

    @Test
    fun testDefault() {
        val (result, _) = server.runRequest("watched_episodes.json") {
            api.ucp
                .watchedEpisodes()
                .build()
                .execute()
        }

        result shouldEqual 3243
    }

    @Test
    fun testPath() {
        val (_, request) = server.runRequest("watched_episodes.json") {
            api.ucp.watchedEpisodes()
                .build()
                .execute()
        }

        request.path shouldEqual "/api/v1/ucp/listsum"
    }
}
