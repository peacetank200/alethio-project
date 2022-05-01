package com.alethio.service.receiving;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alethio.service.catalog.Vendor;

/**
 * 입고 요청 Service 클래스
 */
@Service
public class ReceivingService {
	@Autowired
	private VendorServiceFactory vendorServiceFactory;
	
	@Autowired
	private ReceivingRepository receivingRepository;

	/**
	벤더사에 입고를 요청하는 서비스 메서드
	@param	vendor	벤더사에 해당하는 String 값
	@param	itemId	입고 요청 할 itemId
	@param	itemName	입고 요청 할 품목 이름 
	@param	requestQuantity	입고 요청할 수량
	@return 		입고 요청 성공 여부
	*/
	@Transactional
	public Boolean requestReceiveItem(String vendor, Long itemId, String itemName, int requestQuantity) {
		// Vendor로 해당되는 VendorService 주입
		 Vendor vendorEnum = Vendor.valueOf(vendor.toUpperCase());
		VendorService vendorService = vendorServiceFactory.getService(vendorEnum);
		
		// Vendor사에 맞게 이름 암호화
		String encryptedItemName = vendorService.encryptItemName(itemName);
		
		// 입고요청 테이블에 저장
		ReceivingDTO receivingDto = ReceivingDTO.builder()
				.itemId(itemId)
				.itemName(itemName)
				.encryptedItemName(encryptedItemName)
				.requestQuantity(requestQuantity)
				.vendor(vendorEnum.toString())
				.build();
		receivingRepository.save(ReceivingMapper.INSTANCE.receivingDtoToEntity(receivingDto));
		
		// 실제 요청은 해당 Vendor사 클래스에서 입고요청 처리
		return vendorService.requestReceiveItem(encryptedItemName, requestQuantity);
	}

}
