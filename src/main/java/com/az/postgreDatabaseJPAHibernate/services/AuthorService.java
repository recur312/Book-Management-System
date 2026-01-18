package com.az.postgreDatabaseJPAHibernate.services;

import com.az.postgreDatabaseJPAHibernate.domain.entities.AuthorEntity;
import org.springframework.stereotype.Component;


public interface AuthorService {
    AuthorEntity createAuthor(AuthorEntity authorEntity);

}
