package practice.architecture.ddd.order.application.dto.response

import practice.architecture.ddd.order.domain.model.Order
import java.math.BigDecimal

data class OrderResponse(
    val id: String,
    val customerId: String,
    val status: String,
    val total: BigDecimal,
    val lines: List<Line>
) {
    data class Line(
        val productId: String,
        val unitPrice: BigDecimal,
        val quantity: Int,
        val lineTotal: BigDecimal,
    )

    companion object {
        fun from(order: Order): OrderResponse {
            val lines = order.getLines().map {
                Line(
                    productId = it.productId,
                    unitPrice = it.unitPrice.amount,
                    quantity = it.quantity,
                    lineTotal = it.lineTotal().amount,
                )
            }
            return OrderResponse(
                id = order.id.value,
                customerId = order.customerId.value,
                status = order.getStatus().name,
                total = order.getTotal().amount,
                lines = lines
            )
        }
    }
}
