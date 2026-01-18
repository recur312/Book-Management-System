package com.az.postgreDatabaseJPAHibernate.repositories;

import com.az.postgreDatabaseJPAHibernate.TestDataUtility;
import com.az.postgreDatabaseJPAHibernate.domain.entities.AuthorEntity;
import com.az.postgreDatabaseJPAHibernate.domain.entities.BookEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BookEntityRepositoryIntegrationTests {

    private BookRepository underTest;

    @Autowired
    public BookEntityRepositoryIntegrationTests(BookRepository underTest){
        this.underTest=underTest;
    }

    @Test
    public void testThatBookCanBeCreatedAndFind(){

        AuthorEntity authorEntity = TestDataUtility.createNewAuthorA();
        BookEntity bookEntity = TestDataUtility.createNewBookA(authorEntity);
        underTest.save(bookEntity);
        // Since cascading, the author will also be saved and get a generated id.
        authorEntity.setId(1L);
        Optional<BookEntity> result = underTest.findById(bookEntity.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntity);

    }

    @Test
    public void testMultipleBookCanBeCreatedAndFind(){

        BookEntity bookEntityA = TestDataUtility.createNewBookA(TestDataUtility.createNewAuthorA());
        underTest.save(bookEntityA);


        BookEntity bookEntityB = TestDataUtility.createNewBookB(TestDataUtility.createNewAuthorA());
        underTest.save(bookEntityB);


        BookEntity bookEntityC = TestDataUtility.createNewBookC(TestDataUtility.createNewAuthorA());
        underTest.save(bookEntityC);


        Iterable<BookEntity> result =  underTest.findAll();

        //id of the author is null by default, set them to match the generated id
        bookEntityA.getAuthorEntity().setId(1L);
        bookEntityB.getAuthorEntity().setId(2L);
        bookEntityC.getAuthorEntity().setId(3L);
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(bookEntityA, bookEntityB, bookEntityC);
    }

    @Test
    public void testBooksCanBeUpdated() {
        AuthorEntity authorEntityA = TestDataUtility.createNewAuthorA();


        BookEntity bookEntityA = TestDataUtility.createNewBookA(authorEntityA);

        //Since cascade is enabled, author will be saved with a generated id 1
        underTest.save(bookEntityA);
        //change the default null author id associated with bookA
        bookEntityA.getAuthorEntity().setId(1L);
        bookEntityA.setTitle("UPDATED");
        underTest.save(bookEntityA);




        //Result should be present
        Optional<BookEntity> result = underTest.findById(bookEntityA.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(bookEntityA);

    }

    @Test
    public void testBooksCanBeDeleted() {
        AuthorEntity authorEntityA = TestDataUtility.createNewAuthorA();

        BookEntity bookEntityA = TestDataUtility.createNewBookA(authorEntityA);
        underTest.save(bookEntityA);

        //update the default id of authorA to match the one get saved by cascading
        bookEntityA.getAuthorEntity().setId(1L);
        underTest.delete(bookEntityA);

        Optional<BookEntity> result = underTest.findById(bookEntityA.getIsbn());

        //Result should be empty
        assertThat(result).isEmpty();

    }
}
