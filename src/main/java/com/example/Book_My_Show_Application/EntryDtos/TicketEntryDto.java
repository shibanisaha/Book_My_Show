package com.example.Book_My_Show_Application.EntryDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketEntryDto {
    private int showId;

    private List<String> requestedSeats = new ArrayList<>();
    private int userId;

}
