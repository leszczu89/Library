package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.repository.BookRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public void addBook(Book book){
        bookRepository.save(book);
    }
    public Book getBook(Long id) {
        return bookRepository.findById(id).orElseThrow();
    }
    public Optional<Book> getBookById(Long id) {return bookRepository.getBookById(id);}
    public void deleteBook(Long id) { bookRepository.deleteById(id);}
}
