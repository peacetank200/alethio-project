package com.alethio.service.catalog;

import javax.persistence.*;

import lombok.*;

/**
 * 음식을 관리하는 Entity
 */
@Table(name = "food")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Food extends Item {
    @Column(name = "food_name", nullable = false, length = 30)
    private String foodName;

    @Override
    public String getItemName() {
    	return this.foodName;
    }
}
