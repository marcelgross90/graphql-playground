package rocks.marcelgross.wishlist.tag

import rocks.marcelgross.wishlist.entry.EntryEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity
data class TagEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val title: String,
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        joinColumns = [(JoinColumn(name = "tag_id"))],
        inverseJoinColumns = [(JoinColumn(name = "entry_id"))]
    )
    val entries: List<EntryEntity> = mutableListOf()
)
