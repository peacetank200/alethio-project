package com.alethio.service.receiving;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 입고 요청 Controller 클래스
 */
@RestController
public class ReceivingController {
	@Autowired
	private ReceivingService receivingService;
	
	// TODO MSA 전환시 Controller 작성 필요
}
