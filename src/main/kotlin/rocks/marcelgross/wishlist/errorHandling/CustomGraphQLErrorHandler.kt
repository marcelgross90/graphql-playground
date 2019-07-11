package rocks.marcelgross.wishlist.errorHandling

import graphql.ExceptionWhileDataFetching
import graphql.GraphQLError
import graphql.servlet.core.GraphQLErrorHandler
import org.springframework.stereotype.Component

@Component
class CustomGraphQLErrorHandler : GraphQLErrorHandler {

    override fun processErrors(errors: MutableList<GraphQLError>): MutableList<GraphQLError> {
        val customErrors = mutableListOf<GraphQLError>()

        for (error in errors) {
            if (error is ExceptionWhileDataFetching && error.exception is ForbiddenException) {
                customErrors.add(ForbiddenGraphQLError(error))
            } else {
                customErrors.add(error)
            }
        }

        return customErrors
    }
}