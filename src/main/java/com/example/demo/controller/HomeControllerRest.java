package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.reposetory.BookReposetory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value="api",produces = "application/json")
public class HomeControllerRest {

    private BookReposetory bookReposetory;

    public HomeControllerRest(BookReposetory bookReposetory) {
        this.bookReposetory = bookReposetory;
    }

    @GetMapping
    public Iterable<Book> getAllBooks(){
        return bookReposetory.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookByld(@PathVariable("id") Long id){
        Optional<Book> bookMaybe=bookReposetory.findById(id);
        if(bookMaybe.isPresent()){
            return new ResponseEntity<>(bookMaybe.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    public Book postBook(@RequestBody Book book){
        return bookReposetory.save(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteBook(@PathVariable("id") Long id){
       try{
           bookReposetory.deleteById(id);
       } catch ( EmptyResultDataAccessException e){
           e.printStackTrace();
       }
    }

    @PatchMapping(path="/{id}", consumes="application/json")
    public Book putBook(@PathVariable("id") Long id, @RequestBody Book book){
        Book currentBook = bookReposetory.findById(id).get();
        if (book.getAuthor()!=null){
            currentBook.setAuthor(book.getAuthor());
        };
        if (book.getName()!=null){
            currentBook.setName(book.getName());
        }
        return bookReposetory.save(currentBook);
    }

}
