package rocks.marcelgross.wishlist.entry

import org.springframework.data.domain.Page
import rocks.marcelgross.wishlist.CustomPageImpl
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class EntryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val description: String,
    val url: String
)

data class EntryPage(
    private val page: Page<EntryEntity>
) : CustomPageImpl(
    page.number, page.size, page.totalPages, page.numberOfElements, page.totalElements
) {
    val content = page.content
}
