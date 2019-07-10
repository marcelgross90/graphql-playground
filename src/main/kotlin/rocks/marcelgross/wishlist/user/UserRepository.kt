package rocks.marcelgross.wishlist.user

import org.springframework.data.repository.PagingAndSortingRepository

interface UserRepository : PagingAndSortingRepository<UserEntity, Long> {

    fun findByName(name: String): UserEntity?
}
