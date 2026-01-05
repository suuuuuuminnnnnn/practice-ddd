package practice.architecture.ddd.order.infrastructure.persistence.jpa.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "tbl_order_lines")
class OrderLineJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    var order: OrderJpaEntity,

    @Column(name = "product_id", nullable = false, length = 64)
    var productId: String,

    @Column(name = "unit_price", nullable = false, precision = 19, scale = 2)
    var unitPrice: BigDecimal,

    @Column(name = "quantity", nullable = false)
    var quantity: Int,
) {
    constructor() : this(
        id = null,
        order = OrderJpaEntity(),
        productId = "",
        unitPrice = BigDecimal.ZERO,
        quantity = 1
    )
}