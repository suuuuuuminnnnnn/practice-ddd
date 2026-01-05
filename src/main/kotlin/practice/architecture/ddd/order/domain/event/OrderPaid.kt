package practice.architecture.ddd.order.domain.event

import practice.architecture.ddd.order.domain.model.OrderId

data class OrderPaid(val orderId: OrderId)
