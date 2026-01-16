package com.az.postgreDatabaseJPAHibernate.repositories;

import com.az.postgreDatabaseJPAHibernate.TestDataUtility;
import com.az.postgreDatabaseJPAHibernate.domain.Author;
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
@DirtiesContext( classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorRepositoryIntegrationTests {

    private AuthorRepository underTest;


    @Autowired
    public AuthorRepositoryIntegrationTests(AuthorRepository underTest){
        this.underTest=underTest;
    }
    @Test
    public void testAuthorCanBeCreatedAndFind(){
        Author author = TestDataUtility.createNewAuthorA();
        underTest.save(author);
        Optional<Author> result = underTest.findById(author.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(author);


    }

    @Test
    public void testMultipleAuthorsCanBeCreatedAndFind(){
        Author authorA = TestDataUtility.createNewAuthorA();
        underTest.save(authorA);
        Author authorB = TestDataUtility.createNewAuthorB();
        underTest.save(authorB);
        Author authorC = TestDataUtility.createNewAuthorC();
        underTest.save(authorC);

        Iterable<Author> result = underTest.findAll();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(authorA,authorB,authorC);



    }

    @Test
    public void testAuthorsCanBeUpdated() {
        Author authorA = TestDataUtility.createNewAuthorA();
        underTest.save(authorA);
        authorA.setName("UPDATED");
        underTest.save(authorA);
        Optional<Author> result = underTest.findById(authorA.getId());

        //Result should be present
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorA);

    }

    @Test
    public void testAuthorsCanBeDeleted() {
        Author authorA = TestDataUtility.createNewAuthorA();
        underTest.save(authorA);
        underTest.delete(authorA);

        Optional<Author> result = underTest.findById(authorA.getId());

        //Result should be empty
        assertThat(result).isEmpty();

    }


}
