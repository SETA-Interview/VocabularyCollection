package org.example.collection.vocabulary.utils.mapper;

import org.example.collection.vocabulary.entity.Vocabulary;
import org.example.collection.vocabulary.model.request.VocabularyCreateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface VocabularyRequestMapper extends org.example.collection.vocabulary.utils.mapper.Mapper<VocabularyCreateRequest, Vocabulary> {
	VocabularyRequestMapper INSTANCE = Mappers.getMapper(VocabularyRequestMapper.class);

	@Override
	Vocabulary map(VocabularyCreateRequest source);
}
