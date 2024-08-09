package com.savindu.accountManagement.mapper;

import com.savindu.accountManagement.dto.request.AccountRequestDto;
import com.savindu.accountManagement.dto.response.AccounResponsetDto;
import com.savindu.accountManagement.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    AccounResponsetDto toDto(Account aClass);
    List<AccounResponsetDto> toDtoList(List<Account> aClassList);
    Account toEntity(AccountRequestDto accountRequestDto);
    List<Account> toEntityList(List<AccountRequestDto> accounResponsetDtoList);
}

