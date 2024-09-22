package org.example.collection.vocabulary.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.entity.Collection;
import org.example.collection.vocabulary.entity.Vocabulary;
import org.example.collection.vocabulary.exception.InvalidRequestParamException;
import org.example.collection.vocabulary.model.request.VocabularyCreateRequest;
import org.example.collection.vocabulary.model.request.VocabularyUpdateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.example.collection.vocabulary.repository.VocabularyRepository;
import org.example.collection.vocabulary.service.VocabularyService;
import org.example.collection.vocabulary.utils.mapper.VocabularyRequestMapper;
import org.example.collection.vocabulary.utils.mapper.VocabularyResponseMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VocabularyServiceImpl implements VocabularyService {
	private final VocabularyRepository vocabularyRepository;

	@Override
	public VocabularyResponse findById(UUID id) {
		return VocabularyResponseMapper.INSTANCE.map(vocabularyRepository.findById(id)
																		 .orElseThrow(() -> new InvalidRequestParamException(
																				 "Vocabulary with id " + id + " not found")));
	}

	@Override
	public PageResponse<VocabularyResponse> findAll(int pageNumber, int pageSize, UUID collectionId) {
		return VocabularyResponseMapper.INSTANCE.map(vocabularyRepository.findAllByCollectionId(collectionId, PageRequest.of(pageNumber,
																															 pageSize)));
	}

	@Override
	@Transactional
	public void save(VocabularyCreateRequest request, UUID collectionId) {
		Vocabulary vocabulary = VocabularyRequestMapper.INSTANCE.map(request);
		vocabulary.setCollection(Collection.builder().id(collectionId).build());
		vocabularyRepository.save(vocabulary);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(VocabularyUpdateRequest request) {
		Vocabulary vocabulary = vocabularyRepository.findById(request.getId()).orElseThrow(
				() -> new InvalidRequestParamException("Vocabulary with id " + request.getId() + " not found"));
		vocabulary.setDescription(request.getDescription());
		vocabulary.setName(request.getName());
		vocabularyRepository.save(vocabulary);
	}

	@Override
	public void delete(UUID id) {
		vocabularyRepository.deleteById(id);
	}

	@Override
	public PageResponse<VocabularyResponse> findByName(int pageNumber, int pageSize, String name, UUID collectionId) {
		return VocabularyResponseMapper.INSTANCE.map(
				vocabularyRepository.findAllByNameContainingIgnoreCaseAndCollectionId(name, collectionId, PageRequest.of(pageNumber,
																														 pageSize)));
	}
}
