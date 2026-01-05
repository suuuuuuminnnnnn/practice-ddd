package practice.architecture.ddd.shared.domain

import java.math.BigDecimal

data class Money(val amount: BigDecimal) {
    init {
        require(amount >= BigDecimal.ZERO) { "Money must by >= 0" }
    }

    operator fun plus(other: Money): Money = Money(amount + other.amount)
    operator fun minus(other: Money): Money = Money(amount - other.amount)

    companion object {
        val ZERO = Money(BigDecimal.ZERO)
    }
}