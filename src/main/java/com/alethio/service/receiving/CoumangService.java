package com.alethio.service.receiving;

import org.springframework.stereotype.Service;

import com.alethio.service.catalog.Vendor;

/**
 * 쿠망(Coumang) 밴더 Service 구현체
 * 쿠망 밴더에 해당하는 정보 및 로직을 작성
 */
@Service
public class CoumangService extends VendorService {
	public CoumangService () {
		this.vendor = Vendor.COUMANG;
	}
	
	// 상품명을 암호화 하는 메서드
	@Override
	public String encryptItemName(String itemName) {
		return "123" + itemName;
	}

	// 벤더사에 입고 요청 하는 메서드
	// TODO 로직 확정시 실제 구현 필요
	@Override
	public boolean requestReceiveItem(String itemName, int requestQuantity) {
		return true;
	}

}
