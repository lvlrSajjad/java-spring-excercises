package com.arattastudios.spring5webapp.bootstrap;

import com.arattastudios.spring5webapp.model.Author;
import com.arattastudios.spring5webapp.model.Book;
import com.arattastudios.spring5webapp.model.Publisher;
import com.arattastudios.spring5webapp.repositories.AuthorRepository;
import com.arattastudios.spring5webapp.repositories.BookRepository;
import com.arattastudios.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository,PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData(){
        Author eric = new Author("eric","evans");
        Publisher harper = new Publisher("Harper Collins","SomeWhere");
        Book ddd = new Book("Domain Driven Design","1234",harper);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        publisherRepository.save(harper);

        authorRepository.save(eric);
        bookRepository.save(ddd);

        Author rod = new Author("rod","johnson");
        Publisher worx = new Publisher("Worx","God knows Where");
        Book noEJB = new Book("J2EE Development Without EJB","23444",worx);
        rod.getBooks().add(noEJB);
        publisherRepository.save(worx);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initData();
    }
}
