package com.savindu.account_management.mapper;

import com.savindnu.MicroservicePractice.dto.request.AccountRequestDto;
import com.savindnu.MicroservicePractice.dto.response.AccounResponsetDto;
import com.savindnu.MicroservicePractice.entity.Account;
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

