package com.example.Book_My_Show_Application.Services;

import com.example.Book_My_Show_Application.Convertors.MovieConvertor;
import com.example.Book_My_Show_Application.Entities.MovieEntity;
import com.example.Book_My_Show_Application.EntryDtos.MovieEntryDto;
import com.example.Book_My_Show_Application.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    public String addMovie(MovieEntryDto movieEntryDto)throws Exception,NullPointerException{
        MovieEntity movieEntity = MovieConvertor.convertMovieDtoToEntity(movieEntryDto);

        movieRepository.save(movieEntity);

        return "Movie added successfully";
    }

}
