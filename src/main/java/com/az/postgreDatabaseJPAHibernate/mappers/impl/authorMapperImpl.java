package com.az.postgreDatabaseJPAHibernate.mappers.impl;

import com.az.postgreDatabaseJPAHibernate.domain.dto.AuthorDto;
import com.az.postgreDatabaseJPAHibernate.domain.entities.AuthorEntity;
import com.az.postgreDatabaseJPAHibernate.mappers.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class authorMapperImpl implements Mappers<AuthorEntity, AuthorDto> {
    private ModelMapper modelMapper;

    public authorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto,AuthorEntity.class);
    }
}
