package org.test.bookpub;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.test.bookpub.entity.Author;
import org.test.bookpub.entity.Book;
import org.test.bookpub.entity.Publisher;
import org.test.bookpub.entity.Reviewer;
import org.test.bookpub.repository.AuthorRepository;
import org.test.bookpub.repository.BookRepository;
import org.test.bookpub.repository.PublisherRepository;
import org.test.bookpub.repository.ReviewerRepository;

public class StartupRunner implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());

    @Autowired private BookRepository bookRepository;
    @Autowired private AuthorRepository authorRepository;
    @Autowired private PublisherRepository publisherRepository;
    @Autowired private ReviewerRepository reviewerRepository;
    
    @Autowired private DataSource ds;

    @Override
    public void run(String... args) throws Exception {
        //logger.info("Welcome to the Book Catalog System!");
    	logger.info("Datasource: "+ds);
    	
    	Author author = new Author("Alex", "Antonov");
    	author = authorRepository.save(author);
    	Publisher publisher = new Publisher("Packt");
    	publisher = publisherRepository.save(publisher);
    	Book book = new Book("978-1-78528-415-1", "Spring Boot Recipes", author, publisher);
    	Book book1 = new Book("978-1-78528-415-1.1", "Spring Boot Recipess", author, publisher);

    	Reviewer reviewer1 = new Reviewer("REVIEWER_FIRST_NAME_1", "REVIEWER_LAST_NAME_1");
    	Reviewer reviewer2 = new Reviewer("REVIEWER_FIRST_NAME_2", "REVIEWER_LAST_NAME_2");
    	List<Reviewer> reviewers = new ArrayList<Reviewer>();
    	reviewers.add(reviewer1);
    	reviewers.add(reviewer2);
    	
    	reviewerRepository.save(reviewers);

    	//book1.setReviewers(reviewers);
    	bookRepository.save(book);
    	bookRepository.save(book1);
    	
    }

    @Scheduled(initialDelay = 1000, fixedRate = 10000)
    public void run() {
        logger.info("Number of books: " + bookRepository.count());
    }
}
	