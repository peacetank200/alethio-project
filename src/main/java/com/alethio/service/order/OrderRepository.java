package com.alethio.service.order;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 주문을 관리하는 Entity Repository - JPA 자동 생성으로 구현 불필요
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
}
