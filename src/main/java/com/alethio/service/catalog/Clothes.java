package com.alethio.service.catalog;

import javax.persistence.*;

import lombok.*;

/**
 * 옷을 관리하는 Entity
 */
@Table(name = "clothes")
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Clothes extends Item {
    @Column(name = "clothes_name", nullable = false, length = 30)
    private String clothesName;

    @Override
    public String getItemName() {
    	return this.clothesName;
    }
}
