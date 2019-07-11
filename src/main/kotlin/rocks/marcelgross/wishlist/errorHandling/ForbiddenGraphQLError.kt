package rocks.marcelgross.wishlist.errorHandling

import graphql.ErrorClassification
import graphql.ExceptionWhileDataFetching
import graphql.GraphQLError
import graphql.language.SourceLocation

class ForbiddenGraphQLError(
    private val exception: ExceptionWhileDataFetching
) : GraphQLError {

    override fun getMessage(): String {
        return "Forbidden access to ${exception.path}"
    }

    override fun getLocations(): MutableList<SourceLocation> {
        return exception.locations
    }

    override fun getPath(): MutableList<Any>? {
        return exception.path
    }

    override fun getErrorType(): ErrorClassification? {
        return null
    }
}

class ForbiddenException : Exception()