package com.alethio.service.catalog;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 음식을 관리하는 Entity Repository - JPA 자동 생성으로 구현 불필요
 */
public interface FoodRepository extends JpaRepository<Food, Long> {
}
