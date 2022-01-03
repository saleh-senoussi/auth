package com.bauth.auth.mapper;

//import com.bauth.auth.model.request.UserValidationDto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserValidationMapper {
    UserValidationMapper INSTANCE = Mappers.getMapper(UserValidationMapper.class);

    //UserValidationDto userValidationToUserValidationDto(UserValidationDto userValidation);
}
