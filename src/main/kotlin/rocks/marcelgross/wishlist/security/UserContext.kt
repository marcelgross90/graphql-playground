package rocks.marcelgross.wishlist.security

import rocks.marcelgross.wishlist.user.UserEntity

interface UserContext {
    val user: UserEntity?
    val roles: Set<String>
}
