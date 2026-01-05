package practice.architecture.ddd.order.domain.model

import practice.architecture.ddd.order.domain.event.OrderCreated
import practice.architecture.ddd.order.domain.event.OrderPaid
import practice.architecture.ddd.shared.domain.Money

class Order private constructor(
    val id: OrderId,
    val customerId: CustomerId,
    private val lines: List<OrderLine>,
    private var status: OrderStatus,
    private var total: Money,
){
    private val domainEvents = mutableListOf<Any>()

    init {
        require(lines.isNotEmpty()) { "Order must have at least one line" }
        require(total.amount >= Money.ZERO.amount) { "Total must be >= 0" }
    }

    fun getLines(): List<OrderLine> = lines.toList()
    fun getStatus(): OrderStatus = status
    fun getTotal(): Money = total

    fun pullDomainEvents(): List<Any> =
        domainEvents.toList().also { domainEvents.clear() }

    fun pay() {
        require(status == OrderStatus.CREATED) { "Order must be created to pay" }
        status = OrderStatus.PAID
        domainEvents += OrderPaid(id)
    }

    companion object {
        fun createNew(
            id: OrderId,
            customerId: CustomerId,
            lines: List<OrderLine>,
            total: Money
        ): Order {
            val order = Order(
                id,
                customerId,
                lines,
                OrderStatus.CREATED,
                total
            )
            order.domainEvents += OrderCreated(id)
            return order
        }

        fun restore(
            id: OrderId,
            customerId: CustomerId,
            status: OrderStatus,
            lines: List<OrderLine>,
            total: Money
        ): Order {
            return Order(
                id = id,
                customerId = customerId,
                lines = lines,
                status = status,
                total = total
            )
        }
    }
}