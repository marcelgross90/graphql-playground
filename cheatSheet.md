# Cheat sheet
## User
### Paged user:
```
{ 
  users(page:0, size:10) {
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
### User by id:
```
{ 
  userById(id:1) {
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
## Tag

### Pages tags
```
{ 
  tags(page:0, size: 10) {
      id
      title
      entries {
          id
          description
          url
      }
  }
}
```
### Tag by id
```
{ 
  tagById(id:1) {
      id
      title
      entries {
          id
          description
          url
      }
  }
}
```
### Tag by user id
```
{ 
  tagsByUserId(userId:1) {
      id
      title
      entries {
          id
          description
          url
      }
  }
}
```
## Entry
### Paged entries:
```
{ 
    entries(page: 0, size: 10) {
        id
        description
        url
    }
}
```
### Entry by id:
```
{ 
    entryById(id:1) {
        id
        description
        url
    }
}
```
### Entries by tag id:
```
{ 
    entriesByTagId(tagId:1) {
        id
        description
        url
    }
}
```
