package com.crud.library.repository;

import com.crud.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    Optional<Reader> findReaderById(Long id);
}
