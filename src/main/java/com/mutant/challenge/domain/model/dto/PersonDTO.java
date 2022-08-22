package com.mutant.challenge.domain.model.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import java.io.Serializable;

@Getter @Setter
public class PersonDTO implements Serializable {

    private Long id;
    private String name;
    private String lastname;
    private Integer dni;
    private Boolean isEmployee;

    public PersonDTO(@NonNull String name, @NonNull String lastname, @NonNull Integer dni, @NonNull Boolean isEmployee) {
        this.name = name;
        this.lastname = lastname;
        this.dni = dni;
        this.isEmployee = isEmployee;
    }

}
