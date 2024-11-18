package com.example.eventsystemvalidation.Controller;

import com.example.eventsystemvalidation.ApiResponse.ApiResponse;
import com.example.eventsystemvalidation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<>();

    public EventController(){
        events.add( new Event("EV4156","Boxing event",20, LocalDateTime.of(
                2024, Month.MARCH, 10, 14, 33, 48) ,LocalDateTime.of(
                2024, Month.AUGUST, 10, 14, 33, 48) ));

        events.add( new Event("EV2343","Sing event",20,LocalDateTime.of(
                2024, Month.JANUARY, 24, 14, 33, 48) ,LocalDateTime.of(
                2024, Month.JULY, 24, 14, 33, 48) ));
        events.add( new Event("EV3455","Book Gathering event",20,LocalDateTime.of(
                2024, Month.APRIL, 15, 14, 33, 48) ,LocalDateTime.of(
                2024, Month.DECEMBER, 15, 14, 33, 48) ));

        events.add( new Event("EV7657","Leap event",20,LocalDateTime.of(
                2024, Month.MAY, 24, 14, 33, 48) ,LocalDateTime.of(
                2024, Month.JUNE, 24, 14, 33, 48) ));
        events.add( new Event("EV7688","Football World Cup event",20,LocalDateTime.of(
                2024, Month.APRIL, 21, 14, 33, 48) ,LocalDateTime.of(
                2024, Month.DECEMBER, 21,14, 33, 48) ));
    }

    @GetMapping("/get")
    public ResponseEntity getEvent(){
        return ResponseEntity.ok(events);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity getEvent(@PathVariable String id){

        Event event = null;
        for(Event e : events){
            if(e.getId().toLowerCase().equals(id.toLowerCase())){
                event =e;
            }
        }

        return ResponseEntity.ok(event);
    }
    @PostMapping("/add")
    public ResponseEntity addEvent(@RequestBody @Valid Event event , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        events.add(event);

        return ResponseEntity.status(201).body(new ApiResponse("Event is added"));

    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEvent(@PathVariable int index, @RequestBody @Valid Event event, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(errors.getFieldError().getDefaultMessage()));
        }
        if(index >= events.size() || index < 0) return ResponseEntity.status(400).body(new ApiResponse("Event not found"));

        events.set(index,event);
        return ResponseEntity.status(200).body(new ApiResponse("Event is updated"));
    }

    @PutMapping("/update-capacity/{index}/{capacity}")
    public ResponseEntity updateCapacity(@PathVariable int index ,@PathVariable int capacity){
        if(index >= events.size() || index < 0) return ResponseEntity.status(400).body(new ApiResponse("Event not found"));

        Event event = events.get(index);

        if (capacity > event.getCapacity() || capacity <=0){
            return ResponseEntity.status(400).body(new ApiResponse("capacity is more then available capacity or is zero or less then zero"));
        }

        event.setCapacity(event.getCapacity() - capacity);
        return ResponseEntity.status(200).body(new ApiResponse("Event capacity is updated"));
    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEvent(@PathVariable int index ){
        if(index >= events.size() || index < 0) return ResponseEntity.status(400).body(new ApiResponse("Event not found"));

        events.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("Event is deleted"));
    }

}
