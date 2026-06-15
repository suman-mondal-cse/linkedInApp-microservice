package com.suman.linkedin.connections_service.controller;

import com.suman.linkedin.connections_service.entity.Person;
import com.suman.linkedin.connections_service.service.ConnectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/core")
@RequiredArgsConstructor
public class ConnectionsController {
    private final ConnectionsService connectionsService;


    @PostMapping
    public Person create(@RequestBody Person person) {
        return connectionsService.createPerson(person);
    }

    @PostMapping("/connect")
    public ResponseEntity<String> createConnections(@RequestParam Long userId1, @RequestParam Long userId2){
        connectionsService.connectPerson(userId1,userId2);
        return ResponseEntity.status(HttpStatus.OK).body("Connect");
    }

    @GetMapping("/first-degree")
    public ResponseEntity<List<Person>> getFirstConnections(){
        List<Person> firstDegreeConnections = connectionsService.getFirstDegreeConnections();
        return ResponseEntity.status(HttpStatus.OK).body(firstDegreeConnections);
    }

}
