package com.az.postgreDatabaseJPAHibernate.repositories;

import com.az.postgreDatabaseJPAHibernate.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author,Long> {
    //custom query method, spring data jpa automatically create
    //implementation to this method
    Iterable<Author> ageLessThan(int age);

    //1 means first parameter provided, corresponding to the ?
    @Query("SELECT a from Author a where a.age >?1")
    Iterable<Author> findAuthorWithAgeGreaterThan(int age);
}
