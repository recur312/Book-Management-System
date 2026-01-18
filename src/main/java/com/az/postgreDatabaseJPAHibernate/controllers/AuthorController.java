package com.az.postgreDatabaseJPAHibernate.controllers;

import com.az.postgreDatabaseJPAHibernate.domain.entities.AuthorEntity;
import com.az.postgreDatabaseJPAHibernate.domain.dto.AuthorDto;
import com.az.postgreDatabaseJPAHibernate.mappers.Mappers;
import com.az.postgreDatabaseJPAHibernate.services.AuthorService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {

    private AuthorService authorService;

    private Mappers<AuthorEntity, AuthorDto> authorMapper;


    public AuthorController(AuthorService authorService, Mappers<AuthorEntity,AuthorDto> authorMapper){
        this.authorService= authorService;
        this.authorMapper=authorMapper;
    }

    //Save author entity from http request
    @PostMapping(path = "/authors")
    public AuthorDto createAuthor(@RequestBody AuthorDto author){
        AuthorEntity authorEntity = authorMapper.mapFrom(author);
        AuthorEntity authorEntityToSave = authorService.createAuthor(authorEntity);
        return authorMapper.mapTo(authorEntityToSave);//return data transfer object to caller.
    }



}
