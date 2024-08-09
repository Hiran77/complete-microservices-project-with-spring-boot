package com.savindu.cardManagement.mapper;

import com.savindu.cardManagement.dto.CardDto;
import com.savindu.cardManagement.entity.Card;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CardMapper {
    CardMapper INSTANCE = Mappers.getMapper(CardMapper.class);
    CardDto toDto(Card card);
    List<CardDto> toDtoList(List<Card> aClassList);
    Card toEntity(CardDto accountRequestDto);
    List<Card> toEntityList(List<CardDto> accounResponsetDtoList);
}
