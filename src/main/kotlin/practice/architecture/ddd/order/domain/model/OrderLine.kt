package practice.architecture.ddd.order.domain.model

import practice.architecture.ddd.shared.domain.Money

data class OrderLine(
    val productId: String,
    val unitPrice: Money,
    val quantity: Int,
) {
    init {
        require(productId.isNotBlank()) { "Product id must not be blank" }
        require(quantity > 0) { "Quantity must be > 0" }
    }

    fun lineTotal(): Money =
        Money(unitPrice.amount.multiply(quantity.toBigDecimal()))
}
