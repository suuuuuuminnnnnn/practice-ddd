package practice.architecture.ddd.order.infrastructure.persistence.jpa.repository

import org.springframework.data.jpa.repository.JpaRepository
import practice.architecture.ddd.order.infrastructure.persistence.jpa.entity.OrderLineJpaEntity

interface OrderLineJpaRepository : JpaRepository<OrderLineJpaEntity, Long> {
}