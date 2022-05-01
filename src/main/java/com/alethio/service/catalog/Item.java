package com.alethio.service.catalog;

import javax.persistence.*;

import lombok.*;

/**
 * 품목 엔티티의 슈퍼 클래스
 * 해당 클래스를 상속받아 각 품목별로 엔티티를 구현한다
 */
@MappedSuperclass
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Item {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id; 
   
   @Column(nullable = false)
   private Long quantity; 
   
   public void changeQuanty(Long quantity) {
	   this.quantity = quantity;
   }
   
   public String getItemName() {
	   return null;
   }
}
