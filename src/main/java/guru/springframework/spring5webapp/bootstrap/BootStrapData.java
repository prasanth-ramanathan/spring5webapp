package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Author eric = new Author("Eric", "Evans");
        Book abc = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(abc);
        abc.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(abc);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("blah blah", "98765");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);

        System.out.println("Started Bootstrap");
        System.out.println("No. of books: "+bookRepository.count());

        Publisher oreilly = new Publisher("O'Reilly","123 ABC St","Seattle","WA", "98121");
        publisherRepository.save(oreilly);

        abc.setPublisher(oreilly);
        oreilly.getBooks().add(abc);
        noEJB.setPublisher(oreilly);
        oreilly.getBooks().add(noEJB);
        publisherRepository.save(oreilly);
        System.out.println("No. of publishers: "+publisherRepository.count());
        System.out.println("Publisher info: "+publisherRepository.findAll());
        System.out.println("Publisher info: "+oreilly.getBooks().size());

    }
}
