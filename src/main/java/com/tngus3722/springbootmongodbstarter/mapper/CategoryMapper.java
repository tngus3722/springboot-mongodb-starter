package com.tngus3722.springbootmongodbstarter.mapper;

import com.tngus3722.springbootmongodbstarter.document.CategoryDocument;
import com.tngus3722.springbootmongodbstarter.dto.response.CategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    CategoryResponse toCategoryResponse(CategoryDocument categoryDocument);
}
