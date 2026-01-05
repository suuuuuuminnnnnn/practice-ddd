package practice.architecture.ddd.order.infrastructure.persistence.jpa.mapper

import org.springframework.stereotype.Component
import practice.architecture.ddd.order.domain.model.CustomerId
import practice.architecture.ddd.order.domain.model.Order
import practice.architecture.ddd.order.domain.model.OrderId
import practice.architecture.ddd.order.domain.model.OrderLine
import practice.architecture.ddd.order.domain.model.OrderStatus
import practice.architecture.ddd.order.infrastructure.persistence.jpa.entity.OrderJpaEntity
import practice.architecture.ddd.order.infrastructure.persistence.jpa.entity.OrderLineJpaEntity
import practice.architecture.ddd.shared.domain.Money

@Component
class OrderMapper {
    fun toDomain(entity: OrderJpaEntity): Order {
        val lines = entity.lines.map {
            OrderLine(
                productId = it.productId,
                unitPrice = Money(it.unitPrice),
                quantity = it.quantity,
            )
        }

        return Order.restore(
            id = OrderId(entity.orderId),
            customerId = CustomerId(entity.customerId),
            status = OrderStatus.valueOf(entity.status),
            lines = lines,
            total = Money(entity.totalAmount)
        )
    }

    fun toJpa(domain: Order): OrderJpaEntity {
        val entity = OrderJpaEntity(
            orderId = domain.id.value,
            customerId = domain.customerId.value,
            totalAmount = domain.getTotal().amount,
            status = domain.getStatus().name,
        )

        val lines = domain.getLines().map {
            OrderLineJpaEntity(
                id = null,
                order = entity,
                productId = it.productId,
                unitPrice = it.unitPrice.amount,
                quantity = it.quantity
            )
        }

        entity.lines.clear()
        entity.lines.addAll(lines)
        return entity
    }
}