package org.test.bookpub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.bookpub.entity.Book;
import org.test.bookpub.entity.Isbn;
import org.test.bookpub.entity.Reviewer;
import org.test.bookpub.repository.BookRepository;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookRepository bookRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Iterable<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	/**
	 * String to java object
	 */
	@RequestMapping(value = "/{isbn}/reviewers", method = RequestMethod.GET)
    public List<Reviewer> getReviewers(@PathVariable("isbn") Book book) {
        return book.getReviewers();
    }
}
