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

## Authentication and Authorisation

The endpoint is still accessible without login!
But the collection-attributes `tags` for `users`, `entries` for `tags` is restricted.

For this request:
```
{ 
  users(page:0, size:1) {
    id
    keycloakId
    email
    name
    tags {
        id
        title
        entries {
            id
            description
            url
        }
    }
  }
}
```
the user-roles, `anonymous`, `user`, `admin` will get different responses.

###### Anonymous User:
```json
{
  "data": {
    "users": [
      {
        "id": "1",
        "keycloakId": "keycloakId0",
        "email": "email0@web.de",
        "name": "Name 0",
        "tags": []
      }
    ]
  }
}
```
###### User:
````json
{
    "data": {
        "users": [
            {
                "id": "1",
                "keycloakId": "keycloakId0",            
                "email": "email0@web.de",
                "name": "Name 0",
                "tags": [
                    {
                        "id": "4",
                        "title": "Tag 4",
                        "entries": []
                    },
                    {
                        "id": "7",
                        "title": "Tag 7",
                        "entries": []
                    },
                    ...
                ]
            }
        ]
    }
}
````
###### Admin:
````json
{
    "data": {
        "users": [
            {
                "id": "1",
                "keycloakId": "keycloakId0",
                "email": "email0@web.de",
                "name": "Name 0",
                "tags": [
                    {
                        "id": "4",
                        "title": "Tag 4",
                        "entries": [
                            {
                                "id": "41",
                                "description": "Entry 60",
                                "url": "URL 60"
                            },
                            {
                                "id": "42",
                                "description": "Entry 61",
                                "url": "URL 61"
                            },
                            ...
                        ]
                    },
                    {
                        "id": "7",
                        "title": "Tag 7",
                        "entries": [
                            {
                                "id": "3",
                                "description": "Entry 2",
                                "url": "URL 2"
                            }
                        ]
                    },
                    ...
                ]
            }
        ]
    }
}
````

### Existing User

- User with Role `User`: user/user
- User with Role `Admin`: admin/admin

This example uses the basic authentication workflow. 

## Open Points

1. Pagination:
    1. Return spring page objects for paginated requests
    2. Use Cursor for pagination