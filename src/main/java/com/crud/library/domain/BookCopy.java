package com.crud.library.domain;

import com.crud.library.domain.status.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NamedNativeQuery(
        name = "BookCopy.retrieveBookCopiesWithAvailableStatus",
        query = "SELECT * FROM copies WHERE status='available' AND book_id=:BOOK",
        resultClass = BookCopy.class
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "copies")
public class BookCopy {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "status")
    private String status;

    public BookCopy(Book book, Status status) {
        this.book = book;
        this.status = status.getStatus();
    }
}
