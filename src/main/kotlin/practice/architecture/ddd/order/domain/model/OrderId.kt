package practice.architecture.ddd.order.domain.model

@JvmInline
value class OrderId(val value: String) {
    init {
        require(value.isNotBlank()) { "Order id must not be blank" }
    }

    override fun toString(): String = value
}