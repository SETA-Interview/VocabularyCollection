package org.example.collection.vocabulary.service;

import jakarta.annotation.Nullable;
import org.example.collection.vocabulary.model.request.VocabularyCreateRequest;
import org.example.collection.vocabulary.model.request.VocabularyUpdateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VocabularyService {
	VocabularyResponse findById(UUID id);

	PageResponse<VocabularyResponse> findAll(Pageable pageable, UUID collectionId, @Nullable String name);

	UUID save(VocabularyCreateRequest request, UUID collectionId);

	UUID update(VocabularyUpdateRequest request);

	void delete(UUID id);
}
