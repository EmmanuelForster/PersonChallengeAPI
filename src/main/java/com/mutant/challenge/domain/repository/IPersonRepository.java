package com.mutant.challenge.domain.repository;

import com.mutant.challenge.domain.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IPersonRepository extends JpaRepository<Person, Long> {

    @Query("select p from Person p WHERE p.dni like :dni")
    Optional<Person> getByDni(Integer dni);
}
