package com.mutant.challenge.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Getter @Setter
public class Person {

    @Id
    @SequenceGenerator(name = "person_sequence", sequenceName = "person_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "person_sequence")
    private Long id;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private Integer dni;
    @Column
    private Boolean isEmployee;


    public Person(Long id, String name, String lastname, Integer dni, Boolean isEmployee) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.isEmployee = isEmployee;
    }


    public Person() {
    }

}
