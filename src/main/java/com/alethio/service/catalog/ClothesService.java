package com.alethio.service.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * 옷 품목 Service 구현체
 * 옷 품목에 해당하는 정보 및 로직을 작성
 */
@Service
public class ClothesService extends ItemService {
	public ClothesService(ClothesRepository clothesRepository) {
		this.itemType = ItemType.CLOTHES;
		this.vendor = Vendor.COUMANG;
		this.itemRepository = (JpaRepository) clothesRepository;
	}
}
