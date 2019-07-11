package rocks.marcelgross.wishlist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import rocks.marcelgross.wishlist.tag.TagFactory
import rocks.marcelgross.wishlist.tag.TagRepository
import rocks.marcelgross.wishlist.user.UserFactory
import rocks.marcelgross.wishlist.user.UserRepository

@SpringBootApplication
@EntityScan(basePackages = ["rocks.marcelgross.wishlist"])
@EnableJpaRepositories(basePackages = ["rocks.marcelgross.wishlist"])
class WishlistApplication(
    userRepository: UserRepository,
    tagRepository: TagRepository
) {

    init {
        val user = userRepository.saveAll(UserFactory.generateUsers())
        val tags = tagRepository.saveAll(TagFactory.generateTags())
        userRepository.saveAll(UserFactory.updateUsers(user.toList(), tags.toList()))
    }
}

fun main(args: Array<String>) {
    runApplication<WishlistApplication>(*args)
}