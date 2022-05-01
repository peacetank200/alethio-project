package com.alethio.service.receiving;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * 입고 요청 DTO 클래스와 엔티티간 전환을 위한 Mapper 클래스
 */
@Mapper
public interface ReceivingMapper {
	ReceivingMapper INSTANCE = Mappers.getMapper(ReceivingMapper.class);

    @Mapping(target = "id", constant = "0L")
    Receiving receivingDtoToEntity(ReceivingDTO receivingDto);

    ReceivingDTO receivingToDto(Receiving receiving);
}
