package com.example.Book_My_Show_Application.Convertors;

import com.example.Book_My_Show_Application.Entities.TheaterEntity;
import com.example.Book_My_Show_Application.EntryDtos.TheaterEntryDto;

public class TheaterConvertor {

   public static TheaterEntity convertDtoToEntity(TheaterEntryDto theaterEntryDto){
       return TheaterEntity.builder().name(theaterEntryDto.getName()).location(theaterEntryDto.getLocation())
               .build();

   }
}
