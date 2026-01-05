package practice.architecture.ddd.order.infrastructure.persistence.jpa

import org.springframework.stereotype.Repository
import practice.architecture.ddd.order.domain.model.Order
import practice.architecture.ddd.order.domain.model.OrderId
import practice.architecture.ddd.order.infrastructure.persistence.jpa.mapper.OrderMapper
import practice.architecture.ddd.order.infrastructure.persistence.jpa.repository.OrderJpaRepository
import practice.architecture.ddd.order.repository.OrderRepository

@Repository
class OrderRepositoryImpl(
    private val orderJpaRepository: OrderJpaRepository,
    private val mapper: OrderMapper,
) : OrderRepository {

    override fun save(order: Order): Order {
        val saved = orderJpaRepository.save(mapper.toJpa(order))
        return mapper.toDomain(saved)
    }

    override fun findById(id: OrderId): Order? {
        val found = orderJpaRepository.findById(id.value)
        return found.map { mapper.toDomain(it) }.orElse(null)
    }
}