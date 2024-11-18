package com.example.eventsystemvalidation.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

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
   // @Pattern(regexp = "^[0-9]*$" ,message = "capacity: only number allowed")
    @Min(value = 25,message = "capacity: must enter more then 25")
    private int capacity;
    //@Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "startDate: the date not valid make sure format is yyyy-MM-dd")
    private LocalDateTime startDate;
  //  @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$", message = "LocalDate: the date not valid make sure format is yyyy-MM-dd")
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
