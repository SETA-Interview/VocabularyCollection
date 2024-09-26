package org.example.collection.vocabulary.utils.mapper;

import org.example.collection.vocabulary.entity.Collection;
import org.example.collection.vocabulary.model.response.CollectionResponse;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@org.mapstruct.Mapper(componentModel = SPRING)
public interface CollectionResponseMapper extends Mapper<Collection, CollectionResponse> {
	CollectionResponseMapper INSTANCE = Mappers.getMapper(CollectionResponseMapper.class);

	@Override
	CollectionResponse map(Collection source);
}
