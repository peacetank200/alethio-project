package com.alethio.service.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * 음식 품목 Service 구현체
 * 음식 품목에 해당하는 정보 및 로직을 작성
 */
@Service
public class FoodService extends ItemService {
	public FoodService(FoodRepository foodRepository) {
		this.itemType = ItemType.FOOD;
		this.vendor = Vendor.AMADON;
		this.itemRepository = (JpaRepository) foodRepository;
	}
}
