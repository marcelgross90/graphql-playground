package rocks.marcelgross.wishlist.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope
import rocks.marcelgross.wishlist.user.UserEntity
import rocks.marcelgross.wishlist.user.UserRepository

@Component
@RequestScope
class UserContextImpl(
    private val userRepository: UserRepository
) : UserContext {

    override val user: UserEntity? by lazy {
        val principal = (SecurityContextHolder.getContext().authentication.principal as User)
        var user: UserEntity? = null
        if (SecurityContextHolder.getContext().authentication.isAuthenticated) {
            if (principal.username == "admin") {
                user = userRepository.findById(1).get()
            } else if (principal.username == "user") {
                user = userRepository.findById(2).get()
            }
        }

        user
    }

    override val roles: Set<String> by lazy {
        val set = SecurityContextHolder.getContext().authentication.authorities.map { it.authority }.toSet()

        set
    }
}