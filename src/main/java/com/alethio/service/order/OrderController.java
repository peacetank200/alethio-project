package com.alethio.service.order;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 주문 Controller 클래스
 */
@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	/**
	주문을 생성하는 API 메서드
	@param	orderPostRequest	Order 생성 요청 포맷 DTO 객체
	@return 					성공시 DB에 저장된 Order DTO 객체, 재고 부족 오류 메세지
	*/
	@PostMapping("/order")
	public ResponseEntity<Object> addOrder(@RequestBody OrderPostRequestDTO orderPostRequest) {
		// OrderPostRequestDTO를 OrderDTO로 전환하여 서비스로 요청 
		OrderDTO order = new OrderDTO();
		BeanUtils.copyProperties(orderPostRequest.getContactInfo(), order);
		BeanUtils.copyProperties(orderPostRequest.getItems(), order);
		
		Optional<OrderDTO> returnedOrder = orderService.createOrder(order);
		
		// 요청 결과가 Empty가 아닌 경우 성공, Empty인 경우는 재고 부족
		if (!returnedOrder.isEmpty()) {
			return new ResponseEntity<>(returnedOrder.get(), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Item not enough", HttpStatus.BAD_REQUEST);
		}
	}
}
