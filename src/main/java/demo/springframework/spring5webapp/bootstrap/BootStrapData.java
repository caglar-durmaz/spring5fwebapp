package demo.springframework.spring5webapp.bootstrap;

import demo.springframework.spring5webapp.domain.Author;
import demo.springframework.spring5webapp.domain.Book;
import demo.springframework.spring5webapp.domain.Publisher;
import demo.springframework.spring5webapp.repositories.AuthorRepository;
import demo.springframework.spring5webapp.repositories.BookRepository;
import demo.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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

		System.out.println("Started in Bootstrap");

		Publisher publisher = new Publisher();
		publisher.setName("SFG Publishing");
		publisher.setCity("St Petersburg");
		publisher.setState("FL");

		publisherRepository.save(publisher);

		System.out.println("Publisher: " + publisherRepository.findAll().iterator().next().getName());

		Author eric = new Author("Eric", "Evans");
		Book ddd = new Book("Domain Driven Design", "123123");

		eric.getBooks().add(ddd);
		ddd.getAuthors().add(eric);

		ddd.setPublisher(publisher);
		publisher.getBooks().add(ddd);

		authorRepository.save(eric);
		bookRepository.save(ddd);

		Author rod = new Author("Rod", "Johnson");
		Book noEJB = new Book("J2EE development without EJB", "2342342342");

		rod.getBooks().add(noEJB);
		noEJB.getAuthors().add(rod);

		authorRepository.save(rod);
		bookRepository.save(noEJB);

		noEJB.setPublisher(publisher);
		publisher.getBooks().add(noEJB);

		publisherRepository.save(publisher);

		System.out.println("Number of Books: " + bookRepository.count());
		System.out.println("Publisher Number of Books: " + publisher.getBooks().size() );
	}
}
