package com.restapi.usermanagement.adapter.mapper;

import org.mapstruct.Mapper;

import com.restapi.usermanagement.adapter.input.generic.PaginationResponseDTO;
import com.restapi.usermanagement.domain.model.PaginationResponseModel;

@Mapper(componentModel = "spring")
public interface PaginationMapper {
    PaginationResponseDTO toResponseDTO(PaginationResponseModel model);
}
