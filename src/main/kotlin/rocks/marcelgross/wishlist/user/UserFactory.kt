package rocks.marcelgross.wishlist.user

import rocks.marcelgross.wishlist.tag.TagEntity

object UserFactory {

    fun generateUsers(): List<UserEntity> {
        val users = mutableListOf<UserEntity>()

        for (i in 0 until 3) {
            users.add(
                UserEntity(
                    keycloakId = "keycloakId$i",
                    name = "name$i",
                    email = "email$i@web.de"
                )
            )
        }
        users.add(
                UserEntity(
                        keycloakId = "keycloakId",
                        name = "admin",
                        email = "admin@web.de"
                )
        )

        return users
    }

    fun updateUsers(
        users: List<UserEntity>,
        tags: List<TagEntity>
    ): List<UserEntity> {
        val updatedUser = mutableListOf<UserEntity>()

        for (i in 0 until tags.size) {
            val test = i % (users.size)
            val user = users[i % (users.size)]

            if (updatedUser.size <= (i % (users.size))) {
                updatedUser.add(user.copy(tags = emptyList()))
            } else {
                val currentUser = updatedUser[i % (users.size)]
                val updatedTags = currentUser.tags.toMutableList()
                updatedTags.add(tags[i])
                updatedUser.removeAt(i % (users.size))
                updatedUser.add(i % (users.size), currentUser.copy(tags = updatedTags))
            }
        }

        return updatedUser
    }
}
