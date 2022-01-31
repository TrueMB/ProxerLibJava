package me.proxer.library.api.list

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.list.PersonListEntry
import me.proxer.library.enums.PersonDescriptionType

/**
 * Endpoint for retrieving a list of Persons. Features various filter and uses paging.
 *
 * @author TrueMB
 */
class PersonSearchEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingLimitEndpoint<List<PersonListEntry>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var searchStart: String? = null
    private var contains: String? = null
    private var search: String? = null
    private var subject: PersonDescriptionType? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the query to search for only from the start.
     */
    fun searchStart(searchStart: String?) = this.apply { this.searchStart = searchStart }

    /**
     * Sets the contains String to search for.
     */
    fun contains(contains: String?) = this.apply { this.contains = contains }

    /**
     * Sets the query to search for.
     */
    fun search(search: String?) = this.apply { this.search = search }

    /**
     * Sets the subject (description) in which should be searched. (default: intro)
     */
    fun subject(subject: PersonDescriptionType?) = this.apply { this.subject = subject }

    override fun build(): ProxerCall<List<PersonListEntry>> {
        return internalApi.personList(searchStart, contains, search, subject, page, limit)
    }
}
