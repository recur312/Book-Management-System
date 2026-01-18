package com.az.postgreDatabaseJPAHibernate.repositories;

import com.az.postgreDatabaseJPAHibernate.TestDataUtility;
import com.az.postgreDatabaseJPAHibernate.domain.entities.AuthorEntity;
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
@DirtiesContext( classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class AuthorEntityRepositoryIntegrationTests {

    private AuthorRepository underTest;


    @Autowired
    public AuthorEntityRepositoryIntegrationTests(AuthorRepository underTest){
        this.underTest=underTest;
    }
    @Test
    public void testAuthorCanBeCreatedAndFind(){
        AuthorEntity authorEntity = TestDataUtility.createNewAuthorA();
        underTest.save(authorEntity);
        Optional<AuthorEntity> result = underTest.findById(authorEntity.getId());
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntity);


    }

    @Test
    public void testMultipleAuthorsCanBeCreatedAndFind(){
        AuthorEntity authorEntityA = TestDataUtility.createNewAuthorA();
        underTest.save(authorEntityA);
        AuthorEntity authorEntityB = TestDataUtility.createNewAuthorB();
        underTest.save(authorEntityB);
        AuthorEntity authorEntityC = TestDataUtility.createNewAuthorC();
        underTest.save(authorEntityC);

        Iterable<AuthorEntity> result = underTest.findAll();
        assertThat(result).hasSize(3);
        assertThat(result).containsExactly(authorEntityA, authorEntityB, authorEntityC);



    }

    @Test
    public void testAuthorsCanBeUpdated() {
        AuthorEntity authorEntityA = TestDataUtility.createNewAuthorA();
        underTest.save(authorEntityA);
        authorEntityA.setName("UPDATED");
        underTest.save(authorEntityA);
        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());

        //Result should be present
        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(authorEntityA);

    }

    @Test
    public void testAuthorsCanBeDeleted() {
        AuthorEntity authorEntityA = TestDataUtility.createNewAuthorA();
        underTest.save(authorEntityA);
        underTest.delete(authorEntityA);

        Optional<AuthorEntity> result = underTest.findById(authorEntityA.getId());

        //Result should be empty
        assertThat(result).isEmpty();

    }

    @Test
    public void testGetAuthorAgeLessThan(){
        AuthorEntity authorEntityA = TestDataUtility.createNewAuthorA();
        AuthorEntity authorEntityB = TestDataUtility.createNewAuthorB();
        AuthorEntity authorEntityC = TestDataUtility.createNewAuthorC();

        //save
        underTest.save(authorEntityA);
        underTest.save(authorEntityB);
        underTest.save(authorEntityC);

        //Call the method, expect author B and C
        Iterable<AuthorEntity> result = underTest.ageLessThan(50);

        assertThat(result).containsExactly(authorEntityB, authorEntityC);


    }

    //Custom query using HQL and @Query annotation
    @Test
    public void testGetAuthorAgeGreaterThan(){
        AuthorEntity authorEntityA = TestDataUtility.createNewAuthorA();
        AuthorEntity authorEntityB = TestDataUtility.createNewAuthorB();
        AuthorEntity authorEntityC = TestDataUtility.createNewAuthorC();

        //save
        underTest.save(authorEntityA);
        underTest.save(authorEntityB);
        underTest.save(authorEntityC);

        //Call the method, expect author B and C
        Iterable<AuthorEntity> result = underTest.findAuthorWithAgeGreaterThan(50);

        assertThat(result).containsExactly(authorEntityA);
    }


}
