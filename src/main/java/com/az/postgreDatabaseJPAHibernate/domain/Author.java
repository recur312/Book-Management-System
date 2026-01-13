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
@Entity //labels this object as an entity that can be used with spring data jpa
@Table(name = "authors")//map entity to a database table
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq") //generate id automatically
    private Long id;

    private String name;
    private Integer age;

}
