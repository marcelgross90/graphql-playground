package rocks.marcelgross.wishlist.entry

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class EntryQueryResolver(private val entryRepository: EntryRepository) : GraphQLQueryResolver {

//    fun entries() = entryRepository.findAll()
    fun entries(page: Int, size: Int): Page<EntryEntity> = entryRepository.findAll(PageRequest.of(page, size))
    fun entryById(id: Long) = entryRepository.findByIdOrNull(id)
    fun entriesByTagId(tagId: Long) = entryRepository.findAllForTag(tagId)
}