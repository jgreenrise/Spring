package org.test.bookpub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.test.bookpub.editor.IsbnEditor;
import org.test.bookpub.entity.Book;
import org.test.bookpub.entity.Isbn;
import org.test.bookpub.entity.Reviewer;
import org.test.bookpub.repository.BookRepository;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    @RequestMapping(value = "/{isbn}/reviewers", method = RequestMethod.GET)
    public List<Reviewer> getReviewers(@PathVariable("isbn") Book book) {
    	List<Reviewer> reviewer = book.getReviewers();
        return reviewer;
    }
    
//  @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
//  public Book getBook(@PathVariable String isbn) {
//      return bookRepository.findBookByIsbn(isbn);
//  }
    
    @RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
    public Book getBook(@PathVariable Isbn isbn) {
        return bookRepository.findBookByIsbn(isbn.getIsbn());
    }
    
    @InitBinder
	public void initBinder(WebDataBinder binder) {
	  binder.registerCustomEditor(Isbn.class, new IsbnEditor());
	}
    
}