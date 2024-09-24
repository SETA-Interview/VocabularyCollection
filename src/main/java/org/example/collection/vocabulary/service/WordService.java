package org.example.collection.vocabulary.service;

import jakarta.annotation.Nullable;
import org.example.collection.vocabulary.model.request.WordCreateRequest;
import org.example.collection.vocabulary.model.request.WordUpdateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface WordService {
	WordResponse findById(UUID id);

	PageResponse<WordResponse> findAll(Pageable pageable, UUID collectionId, @Nullable String content);

	UUID save(WordCreateRequest request, UUID vocabularyId);

	UUID update(WordUpdateRequest request);

	void delete(UUID id);
}
