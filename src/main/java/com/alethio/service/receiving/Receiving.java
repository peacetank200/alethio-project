package com.alethio.service.receiving;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 입고 요청을 관리하는 Entity
 */
@Table(name = "receiving_request")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Receiving {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "item_id", nullable = false)
    private Long itemId;

    @Column(name = "item_name", nullable = false, length = 10)
    private String itemName;

    @Column(name = "encrypted_item_name", nullable = false, length = 20)
    private String encryptedItemName;
    
    @Column(nullable = false, length = 10)
    private String vendor;

    @Column(name = "requested_quantity", nullable = false)
    private int requestQuantity;

}