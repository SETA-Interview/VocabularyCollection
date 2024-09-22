package org.example.collection.vocabulary.utils.mapper;

import org.example.collection.vocabulary.entity.Word;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WordResponseMapper extends org.example.collection.vocabulary.utils.mapper.Mapper<Word, WordResponse> {
	WordResponseMapper INSTANCE = Mappers.getMapper(WordResponseMapper.class);
	@Override
	WordResponse map(Word source);
}
