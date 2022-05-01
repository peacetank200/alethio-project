package com.alethio.service.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 품목 Service 클래스
 */
@Service
public class CatalogService {
    @Autowired
    private ItemServiceFactory itemServiceFactory;
    
	/**
	재고를 줄이는 서비스 메서드
	@param	itemType	품목 종류
	@param	itemId		품목 ID
	@return 			재고 존재 여부 Boolean
	*/
	public Boolean decreaseQuantity(String itemType, Long itemId) {
		// ItemType으로 해당되는 ItemService 주입
		ItemType itemTypeEnum = ItemType.valueOf(itemType.toUpperCase());
		ItemService itemService = itemServiceFactory.getService(itemTypeEnum);
		// 실제 수량 감소는 해당 ItemService에서 재고 감소 처리
		return itemService.decreaseQuantity(itemId);
	}
	
	/**
	재고를 늘리는 서비스 메서드
	@param	itemType	품목 종류
	@param	itemId		품목 ID
	@return 			재고 존재 여부 Boolean
	*/
	public Boolean increaseQuantity(String itemType, Long itemId) {
		// TODO 향후 구현 필요
		return false;
	}

}
