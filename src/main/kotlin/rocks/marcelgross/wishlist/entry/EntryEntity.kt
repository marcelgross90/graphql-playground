package rocks.marcelgross.wishlist.entry

import org.springframework.data.domain.PageImpl
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
    val entries: List<EntryEntity>
) : PageImpl<EntryEntity>(entries)