package com.suman.linkedin.connections_service.service;

import com.suman.linkedin.connections_service.auth.UserContextHolder;
import com.suman.linkedin.connections_service.entity.Person;
import com.suman.linkedin.connections_service.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectionsService {
    private final PersonRepository personRepository;

//  Create person node
    public Person createPerson(Person person) {
        log.info("Create person with id: {}", person.getId());
        return personRepository.save(person);
    }

//    Create connection between two person
    public void connectPerson(Long userId1, Long userId2){
        log.info("Trying to connect user id: {} with user id: {}", userId1, userId2);
        personRepository.connectPersons(userId1,userId2);
    }

// Get first degree connections
    public List<Person> getFirstDegreeConnections(){
        Long userId = UserContextHolder.getCurrentUserId();
        log.info("Getting first degree connections for user with id: {}",+userId);
        return personRepository.getFirstDegreeConnections(userId);
    }



}
