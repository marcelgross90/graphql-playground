package rocks.marcelgross.wishlist.entry

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository

interface EntryRepository : PagingAndSortingRepository<EntryEntity, Long> {

    @Query("SELECT e FROM EntryEntity e, TagEntity t WHERE t.id = :tagId AND e MEMBER OF t.entries")
    fun findAllForTag(tagId: Long): List<EntryEntity>
}
