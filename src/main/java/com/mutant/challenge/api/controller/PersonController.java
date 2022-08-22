package com.mutant.challenge.api.controller;

import com.mutant.challenge.exceptions.DNIException;
import com.mutant.challenge.exceptions.IdException;
import com.mutant.challenge.domain.model.dto.PersonDTO;
import com.mutant.challenge.api.service.impl.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/users")
public class PersonController {

    PersonServiceImpl service;

    @Autowired
    public PersonController(PersonServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/create")
    ResponseEntity<String> createPersons(@RequestBody PersonDTO personDTO) throws DNIException {
        return service.createPerson(personDTO);
    }

    @DeleteMapping("/delete")
    ResponseEntity<String> deletePerson(@PathParam("dni") Integer dni) throws DNIException {
        return service.deletePerson(dni);
    }

    @PutMapping("/update")
    ResponseEntity<String> updatePerson(@RequestBody PersonDTO personDTO) throws IdException {
        return service.updatePerson(personDTO);
    }

}
