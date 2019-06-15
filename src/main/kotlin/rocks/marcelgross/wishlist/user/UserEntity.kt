package rocks.marcelgross.wishlist.user

import rocks.marcelgross.wishlist.tag.TagEntity
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
data class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val keycloakId: String,
    val email: String,
    val name: String,
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    @JoinTable(
        joinColumns = [(JoinColumn(name = "user_id"))],
        inverseJoinColumns = [(JoinColumn(name = "tag_id"))]
    )
    val tags: List<TagEntity> = mutableListOf()
)
