package com.alethio.service.order;

import javax.persistence.*;

import org.hibernate.annotations.ColumnTransformer;

import lombok.*;

/**
 * 주문을 관리하는 Entity
 */
@Table(name = "orders")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ColumnTransformer(write = "trim(?)")
    @Column(nullable = false, length = 30)
    private String contactEmail;

    @ColumnTransformer(write = "trim(?)")
    @Column(nullable = false, length = 10)
    private String contactName;

    @ColumnTransformer(write = "trim(?)")
    @Column(nullable = false, length = 13)
    private String mobile;

    @Column(nullable = false, length = 10)
    private String itemType;
    
    @Column(name = "item_id", nullable = false)
    private Long itemId;
}
