package rocks.marcelgross.wishlist.errorHandling

import graphql.ErrorClassification
import graphql.ExceptionWhileDataFetching
import graphql.GraphQLError
import graphql.language.SourceLocation

class ForbiddenGraphQLError(
    private val exceptionWhileDataFetching: ExceptionWhileDataFetching
) : GraphQLError {

    override fun getMessage(): String {
        return "Forbidden access to ${exceptionWhileDataFetching.path}"
    }

    override fun getLocations(): MutableList<SourceLocation> {
        return exceptionWhileDataFetching.locations
    }

    override fun getErrorType(): ErrorClassification? {
        return null
    }

    override fun getPath(): MutableList<Any>? {
        return null
    }
}

class ForbiddenException : Exception()