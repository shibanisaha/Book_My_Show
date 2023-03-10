package com.example.Book_My_Show_Application.Services;

import com.example.Book_My_Show_Application.Convertors.TheaterConvertor;
import com.example.Book_My_Show_Application.Entities.TheaterEntity;
import com.example.Book_My_Show_Application.Entities.TheaterSeatEntity;
import com.example.Book_My_Show_Application.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show_Application.Enums.SeatType;
import com.example.Book_My_Show_Application.Repository.TheaterRepository;
import com.example.Book_My_Show_Application.Repository.TheaterSeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterRepository theaterRepository;


    public String addTheater(TheaterEntryDto theaterEntryDto)throws Exception{
        if(theaterEntryDto.getLocation()==null || theaterEntryDto.getName()==null){
            throw new Exception("Name and location should be valid");
        }
        //creating theater
        TheaterEntity theaterEntity = TheaterConvertor.convertDtoToEntity(theaterEntryDto);

        //creating theaterSeat
        List<TheaterSeatEntity> theaterSeatEntities = createTheaterSeats(theaterEntryDto, theaterEntity);

        theaterEntity.setTheaterSeatEntities(theaterSeatEntities);

        theaterRepository.save(theaterEntity);

        return "Theater added successfully";
    }

    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto, TheaterEntity theaterEntity){

        int noClassicSeat = theaterEntryDto.getClassicSeatCount();
        int noPremiumSeat = theaterEntryDto.getPremiumSeatCount();

        List<TheaterSeatEntity> theaterSeatEntities = new ArrayList<>();

        //Created classic seat
        for(int count =1; count<=noClassicSeat; count++){
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().seatType(SeatType.CLASSIC)
                    .seatNo(count+"C").theaterEntity(theaterEntity).build();
            theaterSeatEntities.add(theaterSeatEntity);
        }

        //created premium seat
        for(int count =1; count<=noPremiumSeat; count++){
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().seatType(SeatType.PREMIUM)
                    .seatNo(count+"P").theaterEntity(theaterEntity).build();
            theaterSeatEntities.add(theaterSeatEntity);
        }

        return theaterSeatEntities;
    }
}
