package org.example.collection.vocabulary.utils.mapper;

import org.example.collection.vocabulary.entity.Collection;
import org.example.collection.vocabulary.model.request.CollectionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CollectionRequestMapper extends org.example.collection.vocabulary.utils.mapper.Mapper<CollectionRequest, Collection> {
	CollectionRequestMapper INSTANCE = Mappers.getMapper(CollectionRequestMapper.class);

	@Override
	Collection map(CollectionRequest source);
}
