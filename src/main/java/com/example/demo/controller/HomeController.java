package com.example.demo.controller;

import com.example.demo.model.Book;
import com.example.demo.reposetory.BookReposetory;
import com.example.demo.service.SoundAnimals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    private BookReposetory bookReposetory;
    private SoundAnimals soundAnimals;

    @Autowired
    public HomeController(BookReposetory bookReposetory, SoundAnimals soundAnimals) {
        this.bookReposetory = bookReposetory;
        this.soundAnimals = soundAnimals;
    }

    @GetMapping
    public String getIndex(Model model) {
        System.out.println("From Controller: "+soundAnimals.sound());
        model.addAttribute("books", bookReposetory.findAll());
        model.addAttribute("newbook", new Book());
        return "index";
    }

    @PostMapping
    public String createBook(Book book){
        bookReposetory.save(book);
        return "redirect:/";
    }

    @GetMapping("/{id}/show")
    public String showById(@PathVariable("id") Long id, Model model){
        if (bookReposetory.findById(id).isPresent()) {
            model.addAttribute("book", bookReposetory.findById(id).get());
                    return "show";
        }
        model.addAttribute("book", new Book());
        return "show";
    }

    @GetMapping("/{id}/delete")
    public String deleteById(@PathVariable("id") Long id){
        bookReposetory.deleteById(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") Long id, Model model){
        if (bookReposetory.findById(id).isPresent()) {
            model.addAttribute("book", bookReposetory.findById(id).get());
            return "edit";
        }
        model.addAttribute("book", new Book());
        return "edit";
    }

    @PostMapping("/{id}")
    public String updateBook(@Valid Book book, Errors errors){
        if (errors.hasErrors()){
            return "edit";
        }
            bookReposetory.save(book);
            return "redirect:/";
    }

}
