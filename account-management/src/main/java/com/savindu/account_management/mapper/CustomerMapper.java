package com.savindu.account_management.mapper;

import com.savindu.account_management.dto.request.CustomerRequestDto;
import com.savindu.account_management.dto.response.CustomerResponseDto;
import com.savindu.account_management.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);
    CustomerResponseDto toDto(Customer aClass);
    List<CustomerResponseDto> toDtoList(List<Customer> aClassList);
    Customer toEntity(CustomerRequestDto request);
    List<Customer> toEntityList(List<CustomerResponseDto> accountDtoList);
}
