package com.alethio.service.receiving;

import com.alethio.service.catalog.Vendor;

import lombok.Getter;

/**
 * 벤더 서비스의 추상 클래스
 * 해당 클래스를 상속받아 각 벤더별로 구현한다
 * 각 벤더사 클래스에서 벤더사 타입, 암호화, 입고요청 로직을 정의한다
 */
@Getter
public abstract class VendorService {
	protected Vendor vendor;
	
	/**
	상품명을 암호화 하는 메서드
	@param	itemName	입고 요청 할 품목 이름 
	@return 			암호화 된 입고 요청 할 품목 이름
	*/
	public abstract String encryptItemName(String itemName);
	
	/**
	벤더사에 입고 요청 하는 메서드
	@param	itemName	암호화 된 입고 요청 할 품목 이름 
	@param	requestQuantity	입고 요청 할 수량 
	@return 			입고 요청 성공 여부
	*/
	public abstract boolean requestReceiveItem(String itemName, int requestQuantity);
}
