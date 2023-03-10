package com.example.Book_My_Show_Application.Services;

import com.example.Book_My_Show_Application.Convertors.TicketConvertor;
import com.example.Book_My_Show_Application.Entities.ShowEntity;
import com.example.Book_My_Show_Application.Entities.ShowSeatEntity;
import com.example.Book_My_Show_Application.Entities.TicketEntity;
import com.example.Book_My_Show_Application.Entities.UserEntity;
import com.example.Book_My_Show_Application.EntryDtos.TicketEntryDto;
import com.example.Book_My_Show_Application.Repository.ShowRepository;
import com.example.Book_My_Show_Application.Repository.TicketRepository;
import com.example.Book_My_Show_Application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class TicketService {
    @Autowired
    ShowRepository showRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TicketRepository ticketRepository;
    public String bookTicket(TicketEntryDto ticketEntryDto)throws Exception{
        //create ticket entity from ticket entry dto
        TicketEntity ticketEntity = TicketConvertor.convertEntryDtoToEntity(ticketEntryDto);

        // check if requested seats are available or not
        boolean isValidRequest = checkValidityOfRequestedSeats(ticketEntryDto);
        if(isValidRequest==false){
            throw new Exception("Requested seats are not available");
        }
        //calculate total amount
        ShowEntity showEntity = showRepository.findById(ticketEntryDto.getShowId()).get();
        List<ShowSeatEntity> showSeatEntities = showEntity.getShowSeatEntityList();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        int amount = 0;
        for(ShowSeatEntity showSeat: showSeatEntities){
            if(requestedSeats.contains(showSeat.getSeatNo())){
                amount+= showSeat.getPrice();
                showSeat.setBooked(true);
                showSeat.setBookedAt(new Date());
            }
        }
        ticketEntity.setTotalAmount(amount);

        //setting other attr
        ticketEntity.setMovieName(showEntity.getMovieEntity().getMovieName());
        ticketEntity.setShowDate(showEntity.getShowDate());
        ticketEntity.setShowTime(showEntity.getShowTime());
        ticketEntity.setTheaterName(showEntity.getTheaterEntity().getName());
        //setting booked seat attr
        ticketEntity.setBookedSeats(bookedSeats(requestedSeats));

        //setting foreign key attr
        UserEntity userEntity = userRepository.findById(ticketEntryDto.getUserId()).get();
        ticketEntity.setUserEntity(userEntity);
        ticketEntity.setShowEntity(showEntity);

        ticketEntity = ticketRepository.save(ticketEntity);

        // save parent
        List<TicketEntity> ticketEntityList = showEntity.getListOfBookedTickets();
        ticketEntityList.add(ticketEntity);
        List<TicketEntity> ticketEntities = userEntity.getBookedTickets();
        ticketEntities.add(ticketEntity);
        //save parent repository
        showRepository.save(showEntity);
        userRepository.save(userEntity);


        //to send conformation mail to user
//        String body = "Hi this is to confirm your booking for seat No "+allocatedSeats +"for the movie : " + ticketEntity.getMovieName();
//
//
//        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
//        MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
//        mimeMessageHelper.setFrom("backeendacciojob@gmail.com");
//        mimeMessageHelper.setTo(userEntity.getEmail());
//        mimeMessageHelper.setText(body);
//        mimeMessageHelper.setSubject("Confirming your booked Ticket");
//
//        javaMailSender.send(mimeMessage);

        return "Ticket booked successfully";
    }

    private String bookedSeats(List<String> requestedSeats){
        String ans = "";
        for(String seat: requestedSeats){
            ans+= seat+","+" ";
        }
        return ans;
    }

    private boolean checkValidityOfRequestedSeats(TicketEntryDto ticketEntryDto){
        int showId = ticketEntryDto.getShowId();
        List<String> requestedSeats = ticketEntryDto.getRequestedSeats();

        ShowEntity showEntity = showRepository.findById(showId).get();
        List<ShowSeatEntity> listOfSeats = showEntity.getShowSeatEntityList();

        for(ShowSeatEntity showSeat: listOfSeats){
            String seatNo = showSeat.getSeatNo();
            if(requestedSeats.contains(seatNo)){
                if(showSeat.isBooked()==true){
                    return false;
                }
            }
        }
        return true;

    }
}
