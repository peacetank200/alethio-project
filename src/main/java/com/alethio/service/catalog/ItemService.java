package com.alethio.service.catalog;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.alethio.service.receiving.ReceivingService;

import lombok.Getter;

/**
 * 품목 서비스의 추상 클래스
 * 해당 클래스를 상속받아 각 품목별로 구현한다
 * 각 품목에서 항목 타입, 벤더사, 리포지토리를 정의한다
 */
@Getter
public abstract class ItemService {
	protected ItemType itemType;
	protected Vendor vendor;
	protected JpaRepository<Item, Long> itemRepository;
	
	// 입고 요청을 관리하는 서비스
	// 향후 MSA 분리시 feignClient 등으로 전환 예정
	@Autowired
	private ReceivingService receivingService;
	
	/**
	재고를 1 줄이는 품목 서비스 메서드
	@param	itemId		품목 ID
	@return 			재고 존재 여부 Boolean
	*/
	@Transactional
	public Boolean decreaseQuantity(Long itemId) {
		JpaRepository<Item, Long> itemRepository = this.getItemRepository();
		Item item = itemRepository.getOne(itemId);
		
		if (item.getQuantity() == 0) {		
			// 아이템 수량이 0인 경우에는 false 반환
			return false;
		} else if (item.getQuantity() == 10) {
			// 아이템 수량이 10인 경우에는 입고요청
			requestReceiveItem(item);
		}
		
		// 아이템 수량을 축소 후 true 반환
		item.changeQuanty(item.getQuantity() - 1);
		itemRepository.save(item);
		return true;
	}
	
	/**
	재고를 1 늘리는 품목 서비스 메서드
	@param	itemId		품목 ID
	@return 			재고 존재 여부 Boolean
	*/
	public Boolean increaseQuantity(Long itemId) {
		// TODO 향후 구현 필요
		return null;
	}

	/**
	입고 요청을 하는 메서드
	@param	item	품목 클래스
	@return 		입고 요청 성공 여부 Boolean
	*/
	public Boolean requestReceiveItem(Item item) {
		return receivingService.requestReceiveItem(this.vendor.toString(), item.getId(), item.getItemName(), 100);
	}
}
