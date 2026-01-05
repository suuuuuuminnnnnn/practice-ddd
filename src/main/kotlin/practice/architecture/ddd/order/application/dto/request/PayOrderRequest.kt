package practice.architecture.ddd.order.application.dto.request

import jakarta.validation.constraints.NotBlank

data class PayOrderRequest(
    @field:NotBlank
    val orderId: String,
)