package guru.springframework.spring5webapp.Bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

   private final AuthorRepository authorRepository;
   private final BookRepository bookRepository;
   private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository,
                         PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Set Publisher Entity
        Publisher anandaPublisher = new Publisher();
        anandaPublisher.setName("Ananda Publishers Pvt. Ltd.");
        anandaPublisher.setAddressLine1("45 Beniatola Lane");
        anandaPublisher.setCity("Kolkata");
        anandaPublisher.setZip("700009");
        anandaPublisher.setState("West Bengal");



        //Set Details for first Book and its relatives
        Author sunil = new Author("Sunil", "Gangopadhayay");
        Book sheiShomoy = new Book("Shei Shomoy","958125");
        sunil.getBooks().add(sheiShomoy);
        sheiShomoy.getAuthors().add(sunil);
        sheiShomoy.setPublisher(anandaPublisher);
        authorRepository.save(sunil);
        bookRepository.save(sheiShomoy);

        //Set Details for second Book and its relative
        Author shirshendu = new Author ("Shirshendu","Mukhopadhayay");
        Book doorBin = new Book("DoorBin","758654");
        doorBin.getAuthors().add(shirshendu);
        shirshendu.getBooks().add(doorBin);
        doorBin.setPublisher(anandaPublisher);
        authorRepository.save(shirshendu);
        bookRepository.save(doorBin);

        //Adding Books to the publisher
        anandaPublisher.getBooks().add(sheiShomoy);
        anandaPublisher.getBooks().add(doorBin);
        publisherRepository.save(anandaPublisher);

        //Testing the repo
        System.out.println("Started Bootstrap");
        System.out.println("number of books "+bookRepository.count() );
        System.out.println("number of authors "+authorRepository.count());
        System.out.println("number of publishers "+publisherRepository.count());

    }
}
