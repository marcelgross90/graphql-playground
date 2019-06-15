package rocks.marcelgross.wishlist.tag

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class TagQueryResolver(private val tagRepository: TagRepository) : GraphQLQueryResolver {

    fun tags(page: Int, size: Int) = tagRepository.findAll(PageRequest.of(page, size))
    fun tagById(id: Long) = tagRepository.findByIdOrNull(id)
    fun tagsByUserId(userId: Long) = tagRepository.findAllForUser(userId)
}