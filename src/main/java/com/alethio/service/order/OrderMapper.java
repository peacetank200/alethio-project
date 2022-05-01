package com.alethio.service.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 주문 DTO 클래스와 엔티티간 전환을 위한 Mapper 클래스
 */
@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "id", constant = "0L")
    Order orderDtoToEntity(OrderDTO orderDto);

    OrderDTO orderToDto(Order order);
}
