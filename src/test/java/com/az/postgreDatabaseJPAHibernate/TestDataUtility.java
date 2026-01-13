package com.az.postgreDatabaseJPAHibernate;

import com.az.postgreDatabaseJPAHibernate.domain.Author;
import com.az.postgreDatabaseJPAHibernate.domain.Book;

public final class TestDataUtility {

    private TestDataUtility(){

    }


    public static Author createNewAuthorA() {
        return Author.builder()
                .id(null)
                .name("Abigail Rose")
                .age(80)
                .build();
    }

    public static Author createNewAuthorB() {
        return Author.builder()
                .id(null)
                .name("James L")
                .age(25)
                .build();
    }

    public static Author createNewAuthorC() {
        return Author.builder()
                .id(null)
                .name("Alex L")
                .age(24)
                .build();
    }



    public static Book createNewBookA(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-0")
                .title("The Shadow in the Attic")
                .author(author)
                .build();
    }

    public static Book createNewBookB(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-1")
                .title("Useless Person")
                .author(author)
                .build();
    }

    public static Book createNewBookC(final Author author) {
        return Book.builder()
                .isbn("978-1-2345-6789-2")
                .title("Rather Die")
                .author(author)
                .build();
    }


}
