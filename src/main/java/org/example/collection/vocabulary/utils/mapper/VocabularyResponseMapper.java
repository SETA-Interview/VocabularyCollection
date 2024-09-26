package org.example.collection.vocabulary.utils.mapper;

import org.example.collection.vocabulary.entity.Vocabulary;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface VocabularyResponseMapper extends org.example.collection.vocabulary.utils.mapper.Mapper<Vocabulary, VocabularyResponse> {
	VocabularyResponseMapper INSTANCE = Mappers.getMapper(VocabularyResponseMapper.class);

	@Override
	VocabularyResponse map(Vocabulary source);
}
