package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "readers")
public class Reader {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "firstname")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "creationdate")
    private LocalDate creationDate;
}
