# GraphQL Playground

## Prerequisites

1. JDK 8
2. Maven 3.x
3. This project is written in Kotlin. You have to enable the kotlin plugin in your ide.

## Getting started

###  Start the project within your ide.

### Start the project on cli

    $ make build
    $ make start
    
### How to use

After startup the server provides the following endpoints:

1.  http://localhost:8080/graphiql  (UI to test graphql)
2.  http://localhost:8080/graphql   (Endpoint to communicate with graphql)

For example queries see `cheatSheet.md`.

## Open Points

1. Authentication and Authorisation
2. Pagination:
    1. Return spring page objects for paginated requests
    2. Use Cursor for pagination