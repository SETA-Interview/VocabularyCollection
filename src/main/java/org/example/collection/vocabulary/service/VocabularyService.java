package org.example.collection.vocabulary.service;

import org.example.collection.vocabulary.model.request.VocabularyCreateRequest;
import org.example.collection.vocabulary.model.request.VocabularyUpdateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;

import java.util.UUID;

public interface VocabularyService {
	VocabularyResponse findById(UUID id);

	PageResponse<VocabularyResponse> findAll(int pageNumber, int pageSize, UUID collectionId);

	void save(VocabularyCreateRequest request, UUID collectionId);

	void update(VocabularyUpdateRequest request);

	void delete(UUID id);

	PageResponse<VocabularyResponse> findByName(int pageNumber, int pageSize, String name, UUID collectionId);
}
