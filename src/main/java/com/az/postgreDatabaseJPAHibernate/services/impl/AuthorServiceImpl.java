package com.az.postgreDatabaseJPAHibernate.services.impl;

import com.az.postgreDatabaseJPAHibernate.domain.entities.AuthorEntity;
import com.az.postgreDatabaseJPAHibernate.repositories.AuthorRepository;
import com.az.postgreDatabaseJPAHibernate.services.AuthorService;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {


    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity createAuthor(AuthorEntity authorEntity) {

        return authorRepository.save(authorEntity);
    }
}
