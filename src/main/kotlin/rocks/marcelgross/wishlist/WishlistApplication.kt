package rocks.marcelgross.wishlist

import com.oembedler.moon.graphql.boot.GraphQLWebAutoConfiguration.QUERY_EXECUTION_STRATEGY
import graphql.execution.AsyncExecutionStrategy
import graphql.execution.ExecutionStrategy
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import rocks.marcelgross.wishlist.errorHandling.CustomDataFetcherExceptionHandler
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


    @Bean(name = [QUERY_EXECUTION_STRATEGY])
    fun executionStrategyProvider(): ExecutionStrategy {
        return AsyncExecutionStrategy(CustomDataFetcherExceptionHandler())
    }

}

fun main(args: Array<String>) {
    runApplication<WishlistApplication>(*args)
}
