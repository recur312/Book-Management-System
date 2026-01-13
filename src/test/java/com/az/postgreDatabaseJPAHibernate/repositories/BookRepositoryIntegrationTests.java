package com.az.postgreDatabaseJPAHibernate.repositories;

import com.az.postgreDatabaseJPAHibernate.TestDataUtility;
import com.az.postgreDatabaseJPAHibernate.domain.Author;
import com.az.postgreDatabaseJPAHibernate.domain.Book;
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
        // Since we use cascading, the author will also be saved and get a generated id.
        author.setId(1L);
        Optional<Book> result = underTest.findById(book.getIsbn());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(book);

    }

//    @Test
//    public void testMultipleBookCanBeCreatedAndFind(){
//        Author author=TestDataUtility.createNewAuthorA();
//        authorDao.create(author);
//
//        Book bookA = TestDataUtility.createNewBookA();
//        bookA.setAuthorId(author.getId());
//        underTest.create(bookA);
//
//        Book bookB = TestDataUtility.createNewBookB();
//        bookB.setAuthorId(author.getId());
//        underTest.create(bookB);
//
//        Book bookC = TestDataUtility.createNewBookC();
//        bookC.setAuthorId(author.getId());
//        underTest.create(bookC);
//
//        List<Book> result =  underTest.find();
//
//        assertThat(result).hasSize(3);
//        assertThat(result).containsExactly(bookA,bookB,bookC);
//    }
//
//    @Test
//    public void testBooksCanBeUpdated() {
//        Author authorA = TestDataUtility.createNewAuthorA();
//        authorDao.create(authorA);
//
//        Book bookA = TestDataUtility.createNewBookA();
//        bookA.setAuthorId(authorA.getId());
//        underTest.create(bookA);
//
//        bookA.setTitle("UPDATED");
//        underTest.update(bookA.getIsbn(),bookA);
//
//
//        //Result should be present
//        Optional<Book> result = underTest.findOne(bookA.getIsbn());
//        assertThat(result).isPresent();
//        assertThat(result.get()).isEqualTo(bookA);
//
//    }
//
//    @Test
//    public void testBooksCanBeDeleted() {
//        Author authorA = TestDataUtility.createNewAuthorA();
//        authorDao.create(authorA);
//
//        Book bookA = TestDataUtility.createNewBookA();
//        bookA.setAuthorId(authorA.getId());
//        underTest.create(bookA);
//
//        underTest.delete(bookA.getIsbn());
//
//        Optional<Book> result = underTest.findOne(bookA.getIsbn());
//
//        //Result should be empty
//        assertThat(result).isEmpty();
//
//    }
}
