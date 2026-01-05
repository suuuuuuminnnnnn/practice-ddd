package practice.architecture.ddd.order.domain.model

@JvmInline
value class CustomerId(val value: String) {
    init {
        require(value.isNotBlank()) { "Customer id must not be blank" }
    }

    override fun toString(): String = value
}