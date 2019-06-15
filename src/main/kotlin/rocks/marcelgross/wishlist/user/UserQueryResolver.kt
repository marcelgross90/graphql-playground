package rocks.marcelgross.wishlist.user

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserQueryResolver(private val userRepository: UserRepository) : GraphQLQueryResolver {

    fun users(page: Int, size: Int) = userRepository.findAll(PageRequest.of(page, size))
    fun userById(id: Long) = userRepository.findByIdOrNull(id)
}