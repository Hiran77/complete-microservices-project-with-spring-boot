package com.savindu.account_management.mapper;

import com.savindnu.MicroservicePractice.dto.request.CustomerRequestDto;
import com.savindnu.MicroservicePractice.dto.response.CustomerResponseDto;
import com.savindnu.MicroservicePractice.entity.Customer;
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
