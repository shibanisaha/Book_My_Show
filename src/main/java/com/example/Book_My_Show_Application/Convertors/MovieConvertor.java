package com.example.Book_My_Show_Application.Convertors;

import com.example.Book_My_Show_Application.Entities.MovieEntity;
import com.example.Book_My_Show_Application.EntryDtos.MovieEntryDto;

public class MovieConvertor {

    public static MovieEntity convertMovieDtoToEntity(MovieEntryDto movieEntryDto){

        MovieEntity movieEntity = MovieEntity.builder().movieName(movieEntryDto.getMovieName()).genre(movieEntryDto.getGenre())
               .rating(movieEntryDto.getRating()).duration(movieEntryDto.getDuration()).language(movieEntryDto.getLanguage()).build();

        return movieEntity;
    }
}
