package rocks.marcelgross.wishlist

import graphql.ExceptionWhileDataFetching
import graphql.GraphQLError
import graphql.servlet.core.GenericGraphQLError
import graphql.servlet.core.GraphQLErrorHandler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class CustomGraphQLErrorHandler : GraphQLErrorHandler {

    val log = LoggerFactory.getLogger(CustomGraphQLErrorHandler::class.java)

    override fun processErrors(errors: MutableList<GraphQLError>): MutableList<GraphQLError> {
        val clientErrors = filterGraphQLErrors(errors).toMutableList()
        if (clientErrors.size < errors.size) {

            // Some errors were filtered out to hide implementation - put a generic error in place.
            clientErrors.add(GenericGraphQLError("Internal Server Error(s) while executing query"))

            errors.stream()
                .filter { error -> !isClientError(error) }
                .forEach { error ->
                    if (error is Throwable) {
                        log.error("Error executing query!", error)
                    } else {
                        log.error("Error executing query ({}): {}", error.javaClass.simpleName, error.getMessage())
                    }
                }
        }

        return clientErrors
    }

    private fun filterGraphQLErrors(errors: List<GraphQLError>): List<GraphQLError> {
        return errors // .filter { isClientError(it) }
    }

    private fun isClientError(error: GraphQLError): Boolean {
        return if (error is ExceptionWhileDataFetching) {
            error.exception is GraphQLError
        } else error !is Throwable
    }
}