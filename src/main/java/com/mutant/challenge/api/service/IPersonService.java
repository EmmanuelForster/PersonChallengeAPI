package com.mutant.challenge.api.service;

import com.mutant.challenge.exceptions.DNIException;
import com.mutant.challenge.exceptions.IdException;
import com.mutant.challenge.domain.model.dto.PersonDTO;
import org.springframework.http.ResponseEntity;

public interface IPersonService {

    ResponseEntity<String> createPerson(PersonDTO person) throws DNIException;

    ResponseEntity<String> deletePerson(Integer dni) throws DNIException;

    ResponseEntity<String> updatePerson(PersonDTO person) throws IdException;
}
