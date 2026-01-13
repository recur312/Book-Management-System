package com.az.postgreDatabaseJPAHibernate.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    private String isbn;
    private String title;


//    private Long authorId;
    //define relationship between book and author
    @ManyToOne(cascade = CascadeType.ALL) //cascade =.., if were to get a book back also retrieve the author, and take that author
    // of the book make some changes to it, those changes will be saved in the database
    @JoinColumn(name = "author_id")
    private Author author;
}
