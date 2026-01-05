package practice.architecture.ddd.order.infrastructure.persistence.jpa.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import java.math.BigDecimal

@Entity
@Table(name = "tbl_orders")
class OrderJpaEntity(
    @Id
    @Column(name = "order_id", nullable = false, length = 64)
    val orderId: String,

    @Column(name = "customer_id", nullable = false, length = 64)
    val customerId: String,

    @Column(name = "status", nullable = false, length = 16)
    val status: String,

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    val totalAmount: BigDecimal,
) {
    @OneToMany(
        mappedBy = "order",
        cascade = [CascadeType.ALL],
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    val lines: MutableList<OrderLineJpaEntity> = mutableListOf()

    constructor() : this(
        orderId = "",
        customerId = "",
        status = "CREATED",
        totalAmount = BigDecimal.ZERO,
    )
}