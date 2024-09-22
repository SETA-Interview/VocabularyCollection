package org.example.collection.vocabulary.utils.mapper;

import org.example.collection.vocabulary.entity.Word;
import org.example.collection.vocabulary.model.request.WordCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WordRequestMapper extends org.example.collection.vocabulary.utils.mapper.Mapper<WordCreateRequest, Word> {
	WordRequestMapper INSTANCE = Mappers.getMapper(WordRequestMapper.class);

	@Override
	Word map(WordCreateRequest source);
}
