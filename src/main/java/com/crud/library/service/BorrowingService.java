package com.crud.library.service;

import com.crud.library.domain.Borrowing;
import com.crud.library.exception.NoSuchElementException;
import com.crud.library.repository.BorrowingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class BorrowingService {

    private final BorrowingRepository borrowingRepository;

    public Borrowing borrowBook(Borrowing borrowing){
        return borrowingRepository.save(borrowing);
    }

    public Borrowing returnBook(Long bookCopyId){
        Borrowing updatedBorrowing = borrowingRepository.findBorrowingsByBookCopy_Id(bookCopyId);
        updatedBorrowing.setReturningDate(LocalDate.now());
        borrowingRepository.save(updatedBorrowing);
        return updatedBorrowing;
    }
    public void deleteBorrowingById(Long borrowingId) {
        borrowingRepository.deleteById(borrowingId);
    }

    public Borrowing getBorrowingById(Long borrowingId) throws NoSuchElementException{
        return borrowingRepository.findById(borrowingId).orElseThrow(NoSuchElementException::new);
    }

}
