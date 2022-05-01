package com.alethio.service.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

/**
 * 주문 생성 요청을 전송하기 위한 DTO 클래스
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderPostRequestDTO {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ContactInfoDTO {
	    private String contactEmail;
	    private String contactName;
	    private String mobile;
	}

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItemsDTO {
	    private String itemType;
	    @JsonProperty("id")
	    private Long itemId;
	}

    private ContactInfoDTO contactInfo;
    private ItemsDTO items;
}
