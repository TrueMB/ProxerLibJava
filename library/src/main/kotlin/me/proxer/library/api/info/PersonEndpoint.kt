package me.proxer.library.api.info

import me.proxer.library.ProxerCall
import me.proxer.library.api.Endpoint
import me.proxer.library.entity.info.Person

/**
 * Endpoint for retrieving all information of a [Person].
 *
 * @author TrueMB
 */
class PersonEndpoint internal constructor(
    private val internalApi: InternalApi,
    private val id: String
) : Endpoint<Person> {

    override fun build(): ProxerCall<Person> {
        return internalApi.person(id)
    }
}
