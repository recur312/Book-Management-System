package com.az.postgreDatabaseJPAHibernate;

import com.az.postgreDatabaseJPAHibernate.domain.entities.AuthorEntity;
import com.az.postgreDatabaseJPAHibernate.domain.entities.BookEntity;

public final class TestDataUtility {

    private TestDataUtility(){

    }


    public static AuthorEntity createNewAuthorA() {
        return AuthorEntity.builder()
                .id(null)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static AuthorEntity createNewAuthorB() {
        return AuthorEntity.builder()
                .id(null)
                .name("James L")
                .age(25)
                .build();
    }

    public static AuthorEntity createNewAuthorC() {
        return AuthorEntity.builder()
                .id(null)
                .name("Alex L")
                .age(24)
                .build();
    }



    public static BookEntity createNewBookA(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createNewBookB(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-1")
                .title("Useless Person")
                .authorEntity(authorEntity)
                .build();
    }

    public static BookEntity createNewBookC(final AuthorEntity authorEntity) {
        return BookEntity.builder()
                .isbn("978-1-2345-6789-2")
                .title("Rather Die")
                .authorEntity(authorEntity)
                .build();
    }


}
