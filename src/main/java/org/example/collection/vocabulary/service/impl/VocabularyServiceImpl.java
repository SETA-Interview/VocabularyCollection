package org.example.collection.vocabulary.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.entity.Collection;
import org.example.collection.vocabulary.entity.Collection_;
import org.example.collection.vocabulary.entity.Vocabulary;
import org.example.collection.vocabulary.entity.Vocabulary_;
import org.example.collection.vocabulary.exception.ResourceNotFoundException;
import org.example.collection.vocabulary.model.request.VocabularyCreateRequest;
import org.example.collection.vocabulary.model.request.VocabularyUpdateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.example.collection.vocabulary.repository.VocabularyRepository;
import org.example.collection.vocabulary.service.VocabularyService;
import org.example.collection.vocabulary.utils.mapper.VocabularyRequestMapper;
import org.example.collection.vocabulary.utils.mapper.VocabularyResponseMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.example.collection.vocabulary.utils.specification.SpecificationBuilder.equalManyToOne;
import static org.example.collection.vocabulary.utils.specification.SpecificationBuilder.likeIgnoreCase;

@Service
@RequiredArgsConstructor
public class VocabularyServiceImpl implements VocabularyService {
	private final VocabularyRepository vocabularyRepository;

	@Override
	public VocabularyResponse findById(UUID id) {
		return VocabularyResponseMapper.INSTANCE.map(vocabularyRepository.findById(id)
																		 .orElseThrow(() -> new ResourceNotFoundException(
																				 "Vocabulary with id " + id + " not found")));
	}

	@Override
	public PageResponse<VocabularyResponse> findAll(Pageable pageable, UUID collectionId, String name) {
		return VocabularyResponseMapper.INSTANCE.map(
				vocabularyRepository.findAll(
						equalManyToOne(Vocabulary_.collection, Collection_.id, collectionId).and(likeIgnoreCase(Vocabulary_.name, name)),
						pageable));
	}

	@Override
	@Transactional
	public UUID save(VocabularyCreateRequest request, UUID collectionId) {
		Vocabulary vocabulary = VocabularyRequestMapper.INSTANCE.map(request);
		vocabulary.setCollection(Collection.builder().id(collectionId).build());
		return vocabularyRepository.save(vocabulary).getId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UUID update(VocabularyUpdateRequest request) {
		Vocabulary vocabulary = vocabularyRepository.findById(request.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Vocabulary with id " + request.getId() + " not found"));
		vocabulary.setDescription(request.getDescription());
		vocabulary.setName(request.getName());
		return vocabularyRepository.save(vocabulary).getId();
	}

	@Override
	public void delete(UUID id) {
		vocabularyRepository.deleteById(id);
	}
}
