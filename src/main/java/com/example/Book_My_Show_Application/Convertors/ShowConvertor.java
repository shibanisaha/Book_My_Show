package com.example.Book_My_Show_Application.Convertors;

import com.example.Book_My_Show_Application.Entities.ShowEntity;
import com.example.Book_My_Show_Application.EntryDtos.ShowEntryDto;

public class ShowConvertor {

    public static ShowEntity convertDtoToShow(ShowEntryDto showEntryDto){
        return ShowEntity.builder().showDate(showEntryDto.getShowDate()).showTime(showEntryDto.getShowTime())
                .showType(showEntryDto.getShowType()).build();
    }
}
