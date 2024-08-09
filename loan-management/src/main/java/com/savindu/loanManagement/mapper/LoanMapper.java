package com.savindu.loanManagement.mapper;

import com.savindu.loanManagement.dto.LoanDto;
import com.savindu.loanManagement.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);
    LoanDto toDto(Loan loan);
    List<LoanDto> toDtoList(List<Loan> aClassList);
    Loan toEntity(LoanDto accountRequestDto);
    List<Loan> toEntityList(List<LoanDto> accounResponsetDtoList);
}
