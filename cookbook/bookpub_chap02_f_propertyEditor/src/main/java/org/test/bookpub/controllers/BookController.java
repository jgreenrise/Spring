package org.test.bookpub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.test.bookpub.editor.IsbnEditor;
import org.test.bookpub.entity.Book;
import org.test.bookpub.entity.Isbn;
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
	@RequestMapping(value = "/{isbn}", method = RequestMethod.GET)
	public Book getBook(@PathVariable Isbn isbn) {
	  return bookRepository.findBookByIsbn(isbn.getIsbn());
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
	  binder.registerCustomEditor(Isbn.class, new IsbnEditor());
	}
}
