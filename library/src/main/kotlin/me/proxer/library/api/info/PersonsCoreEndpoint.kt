package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.info.PersonCore

/**
 * Endpoint for retrieving multiple [PersonCore] Objects for a medium entry.
 *
 * @author TrueMB
 */
class PersonsCoreEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<List<PersonCore>> {

    override fun build(): ProxerCall<List<PersonCore>> {
        return internalApi.persons(id)
    }
}
