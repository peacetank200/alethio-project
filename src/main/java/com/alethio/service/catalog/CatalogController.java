package com.alethio.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 품목 Controller 클래스
 */
@RestController
public class CatalogController {
	@Autowired
	private CatalogService catalogService;
	
	// TODO MSA 전환시 Controller 작성 필요
}
