package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "copies_id")
    private BookCopy bookCopy;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "readers_id")
    private Reader reader;

    @Column(name = "borrow_date")
    private LocalDate borrowingDate;

    @Column(name = "return_date")
    private LocalDate returningDate;

}
