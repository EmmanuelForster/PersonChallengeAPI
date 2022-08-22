package com.mutant.challenge.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mutant.challenge.api.service.IPersonService;
import com.mutant.challenge.exceptions.DNIException;
import com.mutant.challenge.exceptions.IdException;
import com.mutant.challenge.domain.model.Person;
import com.mutant.challenge.domain.model.dto.PersonDTO;
import com.mutant.challenge.domain.repository.IPersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PersonServiceImpl implements IPersonService {

    @Autowired
    private IPersonRepository PersonRepository;

    @Autowired
    private ObjectMapper mapper;

    public PersonServiceImpl(IPersonRepository PersonRepository, ObjectMapper mapper) {
        this.PersonRepository = PersonRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<String> createPerson(PersonDTO personDTO) throws DNIException {
            Person person = mapper.convertValue(personDTO, Person.class);
            if (PersonRepository.getByDni(personDTO.getDni()).isEmpty()) {
                PersonRepository.save(person);
                return ResponseEntity.ok().body("Se ha dado de alta la persona correctamente");
            } else {
                throw new DNIException("El DNI ingresado ya existe en la BBDD");
            }
    }

    @Override
    public ResponseEntity<String> deletePerson(Integer dni) throws DNIException {
            if (PersonRepository.getByDni(dni).isPresent()) {
                PersonRepository.deleteById(PersonRepository.getByDni(dni).get().getId());
                return ResponseEntity.ok().body("Se elimino correctamente la persona con dni " + dni);
            } else {
                throw new DNIException("El DNI ingresado no existe en la BBDD");
            }
    }

    @Override
    public ResponseEntity<String> updatePerson(PersonDTO personDTO) throws IdException {
            Person person = mapper.convertValue(personDTO, Person.class);
        if (PersonRepository.findById(person.getId()).isPresent()) {
            PersonRepository.save(person);
            return ResponseEntity.ok().body("Se modifico correctamente la persona con id " + person.getId());
        }
        else {
            throw new IdException("Verifique haber ingresado el id correctamente");
        }
    }
}
