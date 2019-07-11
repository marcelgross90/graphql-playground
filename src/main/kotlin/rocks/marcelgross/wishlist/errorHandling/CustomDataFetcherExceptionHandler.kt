package rocks.marcelgross.wishlist.errorHandling

import graphql.ExceptionWhileDataFetching
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component


@Primary
@Component
class CustomDataFetcherExceptionHandler : DataFetcherExceptionHandler {

    override fun onException(handlerParameters: DataFetcherExceptionHandlerParameters): DataFetcherExceptionHandlerResult {
        val exception = handlerParameters.exception
        val sourceLocation = handlerParameters.sourceLocation
        val path = handlerParameters.path
        val error = ExceptionWhileDataFetching(path, exception, sourceLocation)
        if (exception !is ForbiddenException)
            log.warn(error.message, exception)
        return DataFetcherExceptionHandlerResult.newResult().error(error).build()
    }

    companion object {
        private val log = LoggerFactory.getLogger(CustomDataFetcherExceptionHandler::class.java)
    }
}
