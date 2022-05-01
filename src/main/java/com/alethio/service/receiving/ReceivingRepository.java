package com.alethio.service.receiving;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 입고 요청을 관리하는 Entity Repository - JPA 자동 생성으로 구현 불필요
 */
public interface ReceivingRepository extends JpaRepository<Receiving, Long>{

}
