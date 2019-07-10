package rocks.marcelgross.wishlist.user

import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Component
import rocks.marcelgross.wishlist.ForbiddenException
import rocks.marcelgross.wishlist.security.UserContext
import rocks.marcelgross.wishlist.tag.TagEntity
import rocks.marcelgross.wishlist.tag.TagRepository

@Component
class UserResolver(
    private val tagRepository: TagRepository,
    private val userContext: UserContext
) : GraphQLResolver<UserEntity> {

    fun tags(user: UserEntity): List<TagEntity> {
        return if (userContext.roles.contains("ROLE_USER")) {
            tagRepository.findAllForUser(user.id)
        } else {
           throw ForbiddenException()
        }
    }
}
