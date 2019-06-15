package rocks.marcelgross.wishlist.user

import com.coxautodev.graphql.tools.GraphQLResolver
import org.springframework.stereotype.Component
import rocks.marcelgross.wishlist.tag.TagRepository

@Component
class UserResolver(
    private val tagRepository: TagRepository
) : GraphQLResolver<UserEntity> {
    fun tags(user: UserEntity) = tagRepository.findAllForUser(user.id)
}