package practice.architecture.ddd.order.domain.factory

import org.springframework.stereotype.Component
import practice.architecture.ddd.order.domain.model.CustomerId
import practice.architecture.ddd.order.domain.model.Order
import practice.architecture.ddd.order.domain.model.OrderId
import practice.architecture.ddd.order.domain.model.OrderLine
import practice.architecture.ddd.shared.domain.Money
import java.util.UUID

@Component
class OrderFactory {
    fun createNew(customerId: CustomerId, lines: List<OrderLine>): Order {
        require(lines.isNotEmpty()) { "Order must have at least one line" }

        val total = lines.fold(Money.ZERO) { acc, line -> acc + line.lineTotal() }
        val id = OrderId(UUID.randomUUID().toString())

        return Order.createNew(
            id = id,
            customerId =  customerId,
            lines = lines,
            total = total
        )
    }
}