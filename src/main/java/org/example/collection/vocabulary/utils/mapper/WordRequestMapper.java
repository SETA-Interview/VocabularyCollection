package org.example.collection.vocabulary.utils.mapper;

import org.example.collection.vocabulary.entity.Vocabulary;
import org.example.collection.vocabulary.entity.Word;
import org.example.collection.vocabulary.entity.Word_;
import org.example.collection.vocabulary.model.request.WordRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WordRequestMapper extends org.example.collection.vocabulary.utils.mapper.Mapper<WordRequest, Word> {
	WordRequestMapper INSTANCE = Mappers.getMapper(WordRequestMapper.class);

	@Override
	@Mapping(source = "vocabularyId", target = Word_.VOCABULARY, qualifiedByName = "mappingVocabularyIdToVocabulary")
	Word map(WordRequest source);

	@Named("mappingVocabularyIdToVocabulary")
	default Vocabulary mappingVocabularyIdToVocabulary(UUID vocabularyId) {
		return Vocabulary.builder().id(vocabularyId).build();
	}
}
