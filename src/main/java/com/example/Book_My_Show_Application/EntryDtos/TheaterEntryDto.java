package com.example.Book_My_Show_Application.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TheaterEntryDto {

    private String name;

    private String location;

    private int classicSeatCount;

    private int premiumSeatCount;

}
