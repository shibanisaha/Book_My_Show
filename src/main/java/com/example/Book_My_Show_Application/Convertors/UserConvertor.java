package com.example.Book_My_Show_Application.Convertors;

import com.example.Book_My_Show_Application.Entities.UserEntity;
import com.example.Book_My_Show_Application.EntryDtos.UserEntryDto;

public class UserConvertor {

    //the function is static to avoid calling it via object/instances
    public static UserEntity convertDtoToEntity(UserEntryDto userEntryDto){
        UserEntity userEntity = UserEntity.builder().age(userEntryDto.getAge()).address(userEntryDto.getAddress()).email(userEntryDto.getEmail())
                .name(userEntryDto.getName()).mobNo(userEntryDto.getMobNo()).build();

        return userEntity;
    }
}
