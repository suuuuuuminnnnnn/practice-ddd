package practice.architecture.ddd.order.application.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import practice.architecture.ddd.order.application.dto.request.PlaceOrderRequest
import practice.architecture.ddd.order.application.dto.response.OrderResponse
import practice.architecture.ddd.order.domain.factory.OrderFactory
import practice.architecture.ddd.order.domain.model.CustomerId
import practice.architecture.ddd.order.domain.model.OrderLine
import practice.architecture.ddd.order.domain.repository.OrderRepository
import practice.architecture.ddd.shared.domain.Money

@Service
class PlaceOrderService(
    private val orderFactory: OrderFactory,
    private val orderRepository: OrderRepository,
) {
    @Transactional
    fun execute(request: PlaceOrderRequest): OrderResponse {
        val customerId = CustomerId(request.customerId)

        val lines = request.lines.map {
            OrderLine(
                productId = it.productId,
                unitPrice = Money(it.unitPrice),
                quantity = it.quantity,
            )
        }

        val order = orderFactory.createNew(customerId, lines)
        orderRepository.save(order)

        order.pullDomainEvents()

        return OrderResponse.from(order)
    }
}