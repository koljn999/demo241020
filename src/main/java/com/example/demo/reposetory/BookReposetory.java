package com.example.demo.reposetory;

import com.example.demo.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookReposetory extends CrudRepository<Book, Long> {
}
