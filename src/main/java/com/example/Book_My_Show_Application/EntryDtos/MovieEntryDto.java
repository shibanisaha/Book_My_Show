package com.example.Book_My_Show_Application.EntryDtos;

import com.example.Book_My_Show_Application.Enums.Genre;
import com.example.Book_My_Show_Application.Enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieEntryDto {


    private String movieName;

    private double rating;

    private int duration;

    private Language language;

    private Genre genre;
}
