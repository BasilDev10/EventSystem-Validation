package com.example.eventsystemvalidation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Event {
    @NotEmpty(message = "id: cannot be empty")
    @Size(min = 2, message = "id: must the length more then 2")
    private String id;
    @NotEmpty(message = "description: cannot be empty")
    @Size(min = 15, message = "description: must the length more then 15")
    private String description;
    @NotNull(message = "capacity: cannot be null")
    @Min(value = 25,message = "capacity: must enter more then 25")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    private int capacity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(){

    }
    public Event(String id, String description,int capacity,String startDate, String endDate){
        this.id=id;
        this.description =description;
        this.capacity = capacity;
        setStartDate(startDate);
        setEndDate(endDate);


    }
    public Event(String id, String description,int capacity,LocalDateTime startDate, LocalDateTime endDate){
        this.id=id;
        this.description =description;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;


    }
    public void setStartDate(String startDate){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        this.startDate = LocalDateTime.parse(startDate+"T00:00:00", formatter);

    }
    public void setEndDate(String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        this.endDate = LocalDateTime.parse(endDate+"T00:00:00",formatter);

    }


}
