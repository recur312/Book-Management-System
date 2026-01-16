package com.az.postgreDatabaseJPAHibernate.repositories;

import com.az.postgreDatabaseJPAHibernate.TestDataUtility;
import com.az.postgreDatabaseJPAHibernate.domain.Author;
import com.az.postgreDatabaseJPAHibernate.domain.Book;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookRepositoryIntegrationTests {

    private BookRepository underTest;

    @Autowired
    public BookRepositoryIntegrationTests(BookRepository underTest){
        this.underTest=underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndFind(){

        Author author = TestDataUtility.createNewAuthorA();
        Book book = TestDataUtility.createNewBookA(author);
        underTest.save(book);
        // Since cascading, the author will also be saved and get a generated id.
        author.setId(1L);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

    @Test
    public void testMultipleBookCanBeCreatedAndFind(){

        Book bookA = TestDataUtility.createNewBookA(TestDataUtility.createNewAuthorA());
        underTest.save(bookA);


        Book bookB = TestDataUtility.createNewBookB(TestDataUtility.createNewAuthorA());
        underTest.save(bookB);


        Book bookC = TestDataUtility.createNewBookC(TestDataUtility.createNewAuthorA());
        underTest.save(bookC);


        Iterable<Book> result =  underTest.findAll();

        //id of the author is null by default, set them to match the generated id
        bookA.getAuthor().setId(1L);
        bookB.getAuthor().setId(2L);
        bookC.getAuthor().setId(3L);
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(bookA,bookB,bookC);
    }

    @Test
    public void testBooksCanBeUpdated() {
        Author authorA = TestDataUtility.createNewAuthorA();


        Book bookA = TestDataUtility.createNewBookA(authorA);

        //Since cascade is enabled, author will be saved with a generated id 1
        underTest.save(bookA);
        //change the default null author id associated with bookA
        bookA.getAuthor().setId(1L);
        bookA.setTitle("UPDATED");
        underTest.save(bookA);




        //Result should be present
        Optional<Book> result = underTest.findById(bookA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookA);

    }

    @Test
    public void testBooksCanBeDeleted() {
        Author authorA = TestDataUtility.createNewAuthorA();

        Book bookA = TestDataUtility.createNewBookA(authorA);
        underTest.save(bookA);

        //update the default id of authorA to match the one get saved by cascading
        bookA.getAuthor().setId(1L);
        underTest.delete(bookA);

        Optional<Book> result = underTest.findById(bookA.getIsbn());

        //Result should be empty
        assertThat(result).isEmpty();

    }
}
