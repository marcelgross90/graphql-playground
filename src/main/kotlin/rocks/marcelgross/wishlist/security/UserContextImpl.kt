package rocks.marcelgross.wishlist.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import rocks.marcelgross.wishlist.user.UserEntity
import rocks.marcelgross.wishlist.user.UserRepository

@Component
@RequestScope
class UserContextImpl(
    private val userRepository: UserRepository
) : UserContext {

    override val user: UserEntity by lazy {
        val authentication = SecurityContextHolder.getContext().authentication
        var user = userRepository.findById(1).get()
        user
    }


    override val roles: Set<String>
        get() = setOf("ADMIN")
}