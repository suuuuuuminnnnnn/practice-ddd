package practice.architecture.ddd.order.presentation.web

import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import practice.architecture.ddd.order.application.dto.request.PayOrderRequest
import practice.architecture.ddd.order.application.dto.request.PlaceOrderRequest
import practice.architecture.ddd.order.application.dto.response.OrderResponse
import practice.architecture.ddd.order.application.service.PayOrderService
import practice.architecture.ddd.order.application.service.PlaceOrderService

@RestController
@RequestMapping("/api/orders")
class OrderController(
    private val placeOrderService: PlaceOrderService,
    private val payOrderService: PayOrderService,
) {
    @PostMapping
    fun placeOrder(@Valid @RequestBody request: PlaceOrderRequest): ResponseEntity<OrderResponse> {
        val response = placeOrderService.execute(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/pay")
    fun pay(@Valid @RequestBody request: PayOrderRequest): ResponseEntity<OrderResponse> {
        val response = payOrderService.execute(request)
        return ResponseEntity.ok(response)
    }
}