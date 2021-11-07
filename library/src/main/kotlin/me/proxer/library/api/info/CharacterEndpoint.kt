package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.info.Character

class CharacterEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<Character> {

    override fun build(): ProxerCall<Character> {
        return internalApi.character(id)
    }

}
