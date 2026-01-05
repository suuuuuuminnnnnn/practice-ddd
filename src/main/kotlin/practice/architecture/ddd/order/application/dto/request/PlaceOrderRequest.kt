package practice.architecture.ddd.order.application.dto.request

import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.PositiveOrZero
import java.math.BigDecimal

data class PlaceOrderRequest(
    @field:NotBlank
    val customerId: String,

    @field:NotEmpty
    @field:Valid
    val lines: List<Line>,
) {
    data class Line(
        @field:NotBlank
        val productId: String,

        @field:PositiveOrZero
        val unitPrice: BigDecimal,

        @field:Positive
        val quantity: Int,
    )
}