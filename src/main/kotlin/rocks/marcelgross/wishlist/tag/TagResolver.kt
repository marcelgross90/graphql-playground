package rocks.marcelgross.wishlist.tag

import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Component
import rocks.marcelgross.wishlist.entry.EntryRepository
import rocks.marcelgross.wishlist.user.UserRepository

@Component
class TagResolver(
    private val userRepository: UserRepository,
    private val entryRepository: EntryRepository
) : GraphQLResolver<TagEntity> {
    fun entries(tag: TagEntity) = entryRepository.findAllForTag(tag.id)
}