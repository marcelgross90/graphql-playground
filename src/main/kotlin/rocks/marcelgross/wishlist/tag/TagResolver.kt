package rocks.marcelgross.wishlist.tag

import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Component
import rocks.marcelgross.wishlist.entry.EntryEntity
import rocks.marcelgross.wishlist.entry.EntryRepository
import rocks.marcelgross.wishlist.errorHandling.ForbiddenException
import rocks.marcelgross.wishlist.security.UserContext

@Component
class TagResolver(
    private val userContext: UserContext,
    private val entryRepository: EntryRepository
) : GraphQLResolver<TagEntity> {
    fun entries(tag: TagEntity): List<EntryEntity> {
        return if (userContext.roles.contains("ROLE_ADMIN")) {
            entryRepository.findAllForTag(tag.id)
        } else {
            throw ForbiddenException()
        }
    }
}