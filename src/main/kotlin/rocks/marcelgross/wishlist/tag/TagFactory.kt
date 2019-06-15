package rocks.marcelgross.wishlist.tag

import rocks.marcelgross.wishlist.entry.EntryFactory

object TagFactory {

    fun generateTags(): List<TagEntity> {
        val entries = EntryFactory.generateEntries()
        val divider = entries.size / 4
        val subEntries1 = entries.subList(0, divider).toMutableList()
        val subEntries2 = entries.subList(divider, divider * 2).toMutableList()
        val subEntries3 = entries.subList(divider * 2, divider * 3).toMutableList()
        val subEntries4 = entries.subList(divider * 3, entries.size).toMutableList()

        val tags = mutableListOf<TagEntity>()

        subEntries1.addAll(subEntries2.subList(0, entries.size / 8))
        subEntries1.addAll(subEntries3.subList(0, entries.size / 8))
        subEntries1.addAll(subEntries4.subList(0, entries.size / 8))

        subEntries2.addAll(subEntries1.subList(0, entries.size / 8))
        subEntries2.addAll(subEntries3.subList(0, entries.size / 8))
        subEntries2.addAll(subEntries4.subList(0, entries.size / 8))

        subEntries3.addAll(subEntries1.subList(0, entries.size / 8))
        subEntries3.addAll(subEntries2.subList(0, entries.size / 8))
        subEntries3.addAll(subEntries4.subList(0, entries.size / 8))

        subEntries3.addAll(subEntries1.subList(0, entries.size / 8))
        subEntries3.addAll(subEntries2.subList(0, entries.size / 8))
        subEntries3.addAll(subEntries3.subList(0, entries.size / 8))

        tags.add(
            TagEntity(
                title = "Tag 1",
                entries = subEntries1
            )
        )

        tags.add(
            TagEntity(
                title = "Tag 2",
                entries = subEntries2
            )
        )

        tags.add(
            TagEntity(
                title = "Tag 3",
                entries = subEntries3
            )
        )

        tags.add(
            TagEntity(
                title = "Tag 4",
                entries = subEntries4
            )
        )

        entries.forEachIndexed { index, entryEntity ->
            tags.add(
                TagEntity(
                    title = "Tag ${index + 5}",
                    entries = listOf(entryEntity)
                )
            )
        }

        return tags
    }
}