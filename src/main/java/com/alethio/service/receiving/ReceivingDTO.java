package com.alethio.service.receiving;

import lombok.*;

/**
 * 입고 요청 데이터를 전송하기 위한 DTO 클래스
 */
@Builder
public @Data class ReceivingDTO {
    private Long id;
    private Long itemId;
    private String itemName;
    private String encryptedItemName;
    private String vendor;
    private int requestQuantity;
}
