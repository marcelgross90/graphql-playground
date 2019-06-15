package rocks.marcelgross.wishlist.tag

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface TagRepository : PagingAndSortingRepository<TagEntity, Long> {

    @Query("SELECT t fROM TagEntity t, UserEntity u WHERE u.id = :userId AND t MEMBER OF u.tags")
    fun findAllForUser(userId: Long): List<TagEntity>
}
