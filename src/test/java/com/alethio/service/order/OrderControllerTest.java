package com.alethio.service.order;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.alethio.service.order.OrderPostRequestDTO.ContactInfoDTO;
import com.alethio.service.order.OrderPostRequestDTO.ItemsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 주문 Controller 테스트 클래스
 */
@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {
	@InjectMocks    
	private OrderController orderController;
	
	@Mock
	private OrderService orderService;

	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
    
	@BeforeEach 
	public void beforeEach() {
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
		objectMapper = Jackson2ObjectMapperBuilder.json().build();
	}

	/**
	 * 주문 생성 성공 테스트
	 * 재고가 있을 경우 성공 케이스 테스트
	 */
	@Test
	@DisplayName("주문 생성 성공 테스트")
	void createOrderSuccess() throws Exception {
		// 요청 DTO
		OrderPostRequestDTO orderRequestDto = new OrderPostRequestDTO();
		ContactInfoDTO contractInfoDto = new ContactInfoDTO();
		ItemsDTO itemsDto = new ItemsDTO();
		contractInfoDto.setContactEmail("test@test.com ");
		contractInfoDto.setContactName("구매자");
		contractInfoDto.setMobile(" 01099999999 ");
		itemsDto.setItemType("clothes");
		itemsDto.setItemId(1L);
		orderRequestDto.setContactInfo(contractInfoDto);
		orderRequestDto.setItems(itemsDto);

		// given에 요청 값으로 주어질 DTO
		OrderDTO orderDto = new OrderDTO();
		orderDto.setContactEmail("test@test.com ");
		orderDto.setContactName("구매자");
		orderDto.setMobile(" 01099999999 ");
		orderDto.setItemType("clothes");
		orderDto.setItemId(1L);
		
		// given에 결과로 주어질 DTO
		OrderDTO resultOrderDto = new OrderDTO();
		resultOrderDto.setId(1L);
		resultOrderDto.setContactEmail("test@test.com");
		resultOrderDto.setContactName("구매자");
		resultOrderDto.setMobile("01099999999");
		resultOrderDto.setItemType("clothes");
		resultOrderDto.setItemId(1L);
		
		// OrderService에서 createOrder 메서드 실행시 결과 목업
		given(orderService.createOrder(orderDto)).willReturn(Optional.of(resultOrderDto));
		
		// 실행
		final ResultActions actions = mockMvc.perform( 
				post("/order")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(orderRequestDto)));
		
		// 결과 확인
		actions
			.andExpect(status().isCreated())
			.andExpect(jsonPath("id").value(1))
			.andExpect(jsonPath("contactEmail").value("test@test.com"))
			.andExpect(jsonPath("contactName").value("구매자"))
			.andExpect(jsonPath("mobile").value("01099999999"))
			.andExpect(jsonPath("itemType").value("clothes"))
			.andExpect(jsonPath("itemId").value(1));
	}
	
	/**
	 * 주문 생성 실패 테스트
	 * 재고가 없을 경우 실패 케이스 테스트
	 */
	@Test
	@DisplayName("주문 생성 실패 테스트")
	void createOrderFailure() throws Exception {
		// 요청 DTO
		OrderPostRequestDTO orderRequestDto = new OrderPostRequestDTO();
		ContactInfoDTO contractInfoDto = new ContactInfoDTO();
		ItemsDTO itemsDto = new ItemsDTO();
		contractInfoDto.setContactEmail("test@test.com ");
		contractInfoDto.setContactName("구매자");
		contractInfoDto.setMobile(" 01099999999 ");
		itemsDto.setItemType("clothes");
		itemsDto.setItemId(1L);
		orderRequestDto.setContactInfo(contractInfoDto);
		orderRequestDto.setItems(itemsDto);

		// given에 요청 값으로 주어질 DTO
		OrderDTO orderDto = new OrderDTO();
		orderDto.setContactEmail("test@test.com ");
		orderDto.setContactName("구매자");
		orderDto.setMobile(" 01099999999 ");
		orderDto.setItemType("clothes");
		orderDto.setItemId(1L);
		
		// OrderService에서 createOrder 메서드 실행시 결과 목업
		given(orderService.createOrder(orderDto)).willReturn(Optional.empty());
		
		// 실행
		final ResultActions actions = mockMvc.perform( 
				post("/order")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.characterEncoding("UTF-8")
					.content(objectMapper.writeValueAsString(orderRequestDto)));
		
		// 결과 확인
		actions
			.andExpect(status().isBadRequest())
			.andExpect(content().string("Item not enough"));}
}
