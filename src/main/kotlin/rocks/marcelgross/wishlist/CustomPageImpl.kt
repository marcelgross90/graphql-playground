package rocks.marcelgross.wishlist

abstract class CustomPageImpl(
    val number: Int = 0,
    val size: Int = 0,
    val totalPages: Int = 0,
    val numberOfElements: Int = 0,
    val totalElements: Long = 0
) {
    val hasNext: Boolean = number + 1 < totalPages
    val isLast: Boolean = !hasNext
}
