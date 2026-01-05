package practice.architecture.ddd.order.application.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import practice.architecture.ddd.order.application.dto.request.PayOrderRequest
import practice.architecture.ddd.order.application.dto.response.OrderResponse
import practice.architecture.ddd.order.domain.model.OrderId
import practice.architecture.ddd.order.domain.repository.OrderRepository

@Service
class PayOrderService(
    private val orderRepository: OrderRepository,
) {
    @Transactional
    fun execute(request: PayOrderRequest): OrderResponse {
        val orderId = OrderId(request.orderId)
        val order = orderRepository.findById(orderId)
            ?: throw IllegalArgumentException("Order not found: ${orderId.value}")

        order.pay()
        orderRepository.save(order)

        return OrderResponse.from(order)
    }
}