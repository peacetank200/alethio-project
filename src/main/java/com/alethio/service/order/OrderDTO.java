package com.alethio.service.order;

import lombok.Data;

/**
 * 주문 데이터를 전송하기 위한 DTO 클래스
 */
public @Data class OrderDTO {
    private Long id;
    private String contactEmail;
    private String contactName;
    private String mobile;
    private String itemType;
    private Long itemId;
}
