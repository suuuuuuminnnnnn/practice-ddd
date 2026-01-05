package practice.architecture.ddd.order.domain.event

import practice.architecture.ddd.order.domain.model.OrderId

data class OrderCreated(val orderId: OrderId)