package practice.architecture.ddd.order.domain.repository

import practice.architecture.ddd.order.domain.model.Order
import practice.architecture.ddd.order.domain.model.OrderId

interface OrderRepository {
    fun save(order: Order): Order
    fun findById(id: OrderId): Order?
}