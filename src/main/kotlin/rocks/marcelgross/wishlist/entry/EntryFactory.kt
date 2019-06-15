package rocks.marcelgross.wishlist.entry

object EntryFactory {

    fun generateEntries(): List<EntryEntity> {
        val entries = mutableListOf<EntryEntity>()

        for (i in 0 until 80) {
            entries.add(
                EntryEntity(
                    description = "Entry $i",
                    url = "URL $i"
                )
            )
        }

        return entries
    }
}