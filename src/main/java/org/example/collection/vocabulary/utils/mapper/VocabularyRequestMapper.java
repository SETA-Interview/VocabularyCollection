package org.example.collection.vocabulary.utils.mapper;

import org.example.collection.vocabulary.entity.Collection;
import org.example.collection.vocabulary.entity.Vocabulary;
import org.example.collection.vocabulary.model.request.VocabularyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface VocabularyRequestMapper extends org.example.collection.vocabulary.utils.mapper.Mapper<VocabularyRequest, Vocabulary> {
	VocabularyRequestMapper INSTANCE = Mappers.getMapper(VocabularyRequestMapper.class);

	@Override
	@Mapping(source = "collectionId", target = "collection", qualifiedByName = "mappingCollectionIdToCollection")
	Vocabulary map(VocabularyRequest source);

	@Named("mappingCollectionIdToCollection")
	default Collection mappingCollectionIdToCollection(UUID collectionId) {
		return Collection.builder().id(collectionId).build();
	}
}
