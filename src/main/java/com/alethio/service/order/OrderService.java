package com.alethio.service.order;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alethio.service.catalog.CatalogService;

/**
 * 주문 Service 클래스
 */
@Service
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	
	// 품목을 관리하는 서비스
	// 향후 MSA 분리시 feignClient 등으로 전환 예정
	@Autowired
	private CatalogService catalogService;

	@Autowired
	private EntityManager em;
	
	/**
	주문을 생성하는 서비스 메서드
	@param	orderDto	Order 값이 담긴 DTO 객체
	@return 			DB에 저장된 Order 값이 담긴 DTO 객체
	*/
	@Transactional
	public Optional<OrderDTO> createOrder(OrderDTO orderDto) {
		// 주문을 생성하는 서비스 메서드
		// Item에 재고 감소 요청
		Boolean itemAvailablity = catalogService.decreaseQuantity(orderDto.getItemType(), orderDto.getItemId());
		
		if (itemAvailablity) {
			// Item이 있을 경우 Order 저장 후 Order를 반환
			Order order = OrderMapper.INSTANCE.orderDtoToEntity(orderDto);
			order = orderRepository.save(order);
			// 저장시 공백 Trim 처리를 DB에 위임하므로, 엔티티가 저장 되어도 id 값만 갱신된다.
			// Trim 된 값을 엔티티에 갱신하기 위하여 refresh 실행
			em.refresh(order); 
			return Optional.of(OrderMapper.INSTANCE.orderToDto(order));
		} else {
			// Item이 없을 경우 빈 값을 반환
			return Optional.empty();
		}
			
	}
}
