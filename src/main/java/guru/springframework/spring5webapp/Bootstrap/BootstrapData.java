package guru.springframework.spring5webapp.Bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

   private final AuthorRepository authorRepository;
   private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        //Author 1
        Author sunil = new Author("Sunil", "Gangopadhayay");
        Book sheiShomoy = new Book("Shei Shomoy","958125");
        sunil.getBooks().add(sheiShomoy);
        sheiShomoy.getAuthors().add(sunil);
        authorRepository.save(sunil);
        bookRepository.save(sheiShomoy);
        //Author 2
        Author shirshendu = new Author ("Shirshendu","Mukhopadhayay");
        Book doorBin = new Book("DoorBin","758654");
        doorBin.getAuthors().add(shirshendu);
        shirshendu.getBooks().add(doorBin);
        authorRepository.save(shirshendu);
        bookRepository.save(doorBin);
        //Testing the repo
        System.out.println("Started Bootstrap");
        System.out.println("number of books "+bookRepository.count() );
    }
}
