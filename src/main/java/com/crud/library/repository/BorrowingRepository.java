package com.crud.library.repository;

import com.crud.library.domain.Borrowing;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface BorrowingRepository extends CrudRepository<Borrowing, Long> {

    Borrowing findBorrowingsByBookCopy_Id(Long id);
}
