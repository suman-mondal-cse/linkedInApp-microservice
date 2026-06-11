package com.suman.linkedin.connections_service.repository;

import com.suman.linkedin.connections_service.entity.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends Neo4jRepository<Person,Long> {
    Optional<Person> getByName(String name);

//    Get first degree connection from DB
    @Query("MATCH(personA:Person) -[:CONNECTED_TO]-(personB:Person) WHERE personA.userId = $userId RETURN personB")
    List<Person> getFirstDegreeConnections(Long userId);


//    Create connection between two person.
    @Query("""
                MATCH (p1:Person {userId: $userId1}), (p2:Person {userId: $userId2})
                MERGE (p1)-[:CONNECTED_TO]-(p2)
                """)
    void connectPersons(Long userId1, Long userId2);

}
