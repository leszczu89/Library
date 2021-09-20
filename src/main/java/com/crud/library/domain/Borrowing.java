package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "copies_id")
    private BookCopy bookCopy;

    @OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "readers_id")
    private Reader reader;

    @Column(name = "borrow_date")
    private LocalDate borrowingDate;

    @Column(name = "return_date")
    private LocalDate returningDate;

    public Borrowing(BookCopy bookCopy, Reader reader, LocalDate borrowingDate) {
        this.bookCopy = bookCopy;
        this.reader = reader;
        this.borrowingDate = borrowingDate;
    }
}
