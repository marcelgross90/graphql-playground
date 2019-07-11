package rocks.marcelgross.wishlist.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import rocks.marcelgross.wishlist.errorHandling.ForbiddenException
import rocks.marcelgross.wishlist.user.UserEntity
import rocks.marcelgross.wishlist.user.UserRepository

@Component
@RequestScope
class UserContextImpl(
    private val userRepository: UserRepository
) : UserContext {

    override val user: UserEntity? by lazy {
        val principal = SecurityContextHolder.getContext().authentication.principal as String
        var user: UserEntity? = null
        if (SecurityContextHolder.getContext().authentication.isAuthenticated) {
            user = userRepository.findByName(principal)
        }
        if (user == null) {
            throw ForbiddenException()
        }
        user
    }

    override val roles: Set<String> by lazy {
        val set = SecurityContextHolder.getContext().authentication.authorities.map { it.authority }.toSet()

        set
    }
}
