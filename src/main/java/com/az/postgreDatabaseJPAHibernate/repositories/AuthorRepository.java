package com.az.postgreDatabaseJPAHibernate.repositories;

import com.az.postgreDatabaseJPAHibernate.domain.entities.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<AuthorEntity,Long> {
    //custom query method, spring data jpa automatically create
    //implementation to this method
    Iterable<AuthorEntity> ageLessThan(int age);

    //1 means first parameter provided, corresponding to the ?
    @Query("SELECT a from AuthorEntity a where a.age >?1")
    Iterable<AuthorEntity> findAuthorWithAgeGreaterThan(int age);
}
