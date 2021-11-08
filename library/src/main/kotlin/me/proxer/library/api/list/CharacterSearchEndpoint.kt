package me.proxer.library.api.list

import me.proxer.library.ProxerCall
import me.proxer.library.api.PagingLimitEndpoint
import me.proxer.library.entity.list.CharacterListEntry
import me.proxer.library.enums.DescriptionType

/**
 * Search for all available characters. Features various filter and uses paging.
 *
 * @author TrueMB
 */
class CharacterSearchEndpoint internal constructor(
    private val internalApi: InternalApi
) : PagingLimitEndpoint<List<CharacterListEntry>> {

    private var page: Int? = null
    private var limit: Int? = null

    private var start: String? = null
    private var contains: String? = null
    private var search: String? = null
    private var subject: DescriptionType? = null

    override fun page(page: Int?) = this.apply { this.page = page }
    override fun limit(limit: Int?) = this.apply { this.limit = limit }

    /**
     * Sets the start String to search for.
     */
    fun start(start: String?) = this.apply { this.start = start }

    /**
     * Sets the contains String to search for.
     */
    fun contains(contains: String?) = this.apply { this.contains = contains }

    /**
     * Sets the search String to search for in the descriptions.
     */
    fun search(search: String?) = this.apply { this.search = search }

    /**
     * Sets the subject that should be looked in.
     */
    fun subject(subject: DescriptionType?) = this.apply { this.subject = subject }

    override fun build(): ProxerCall<List<CharacterListEntry>> {
        return internalApi.characterSearch(start, contains, search, subject, page, limit)
    }
}
