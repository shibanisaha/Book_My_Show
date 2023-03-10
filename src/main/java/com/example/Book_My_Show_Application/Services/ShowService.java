package com.example.Book_My_Show_Application.Services;

import com.example.Book_My_Show_Application.Convertors.ShowConvertor;
import com.example.Book_My_Show_Application.Entities.*;
import com.example.Book_My_Show_Application.EntryDtos.ShowEntryDto;
import com.example.Book_My_Show_Application.Enums.SeatType;
import com.example.Book_My_Show_Application.Repository.MovieRepository;
import com.example.Book_My_Show_Application.Repository.ShowRepository;
import com.example.Book_My_Show_Application.Repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ShowService {

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    TheaterRepository theaterRepository;
    @Autowired
    ShowRepository showRepository;

    public String createShow(ShowEntryDto showEntryDto)throws Exception{

        //create show entity
        ShowEntity showEntity = ShowConvertor.convertDtoToShow(showEntryDto);

        MovieEntity movieEntity = movieRepository.findById(showEntryDto.getMovieId()).get();
        TheaterEntity theaterEntity = theaterRepository.findById(showEntryDto.getTheaterId()).get();
        //set show entity attr;
        showEntity.setMovieEntity(movieEntity);
        showEntity.setTheaterEntity(theaterEntity);

        //adding show seat in show entity
        List<ShowSeatEntity> showSeatEntityList = createShowSeat(showEntryDto, showEntity);
        showEntity.setShowSeatEntityList(showSeatEntityList);

        showEntity = showRepository.save(showEntity);


        movieEntity.getShowEntityList().add(showEntity);
        theaterEntity.getShowEntityList().add(showEntity);

        theaterRepository.save(theaterEntity);
        movieRepository.save(movieEntity);

        return "Show added successfully";
    }
    private List<ShowSeatEntity> createShowSeat(ShowEntryDto showEntryDto, ShowEntity showEntity){

        //create show seat entity
        TheaterEntity theaterEntity = showEntity.getTheaterEntity();
        List<TheaterSeatEntity> theaterSeatEntityList = theaterEntity.getTheaterSeatEntities();
        List<ShowSeatEntity> showSeatEntityList = new ArrayList<>();

        for(TheaterSeatEntity theaterSeatEntity: theaterSeatEntityList){
            ShowSeatEntity showSeatEntity = new ShowSeatEntity();

            showSeatEntity.setSeatNo(theaterSeatEntity.getSeatNo());
            showSeatEntity.setSeatType(theaterSeatEntity.getSeatType());

            if(theaterSeatEntity.getSeatType().equals(SeatType.CLASSIC)){
                showSeatEntity.setPrice(showEntryDto.getClassicSeatPrice());
            }else {
                showSeatEntity.setPrice(showEntryDto.getPremiumSeatPrice());
            }
            showSeatEntity.setBooked(false);
            showSeatEntity.setShowEntity(showEntity);
            showSeatEntityList.add(showSeatEntity);
        }
        return showSeatEntityList;

    }
}
