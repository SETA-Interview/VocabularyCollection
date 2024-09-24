package org.example.collection.vocabulary.service;

import org.example.collection.vocabulary.model.request.WordCreateRequest;
import org.example.collection.vocabulary.model.request.WordUpdateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.WordResponse;

import java.util.UUID;

public interface WordService {
	WordResponse findById(UUID id);

	PageResponse<WordResponse> findAll(int pageNumber, int pageSize, UUID collectionId);

	UUID save(WordCreateRequest request, UUID vocabularyId);

	UUID update(WordUpdateRequest request);

	void delete(UUID id);

	PageResponse<WordResponse> findByContent(int pageNumber, int pageSize, UUID vocabularyId, String content);
}
