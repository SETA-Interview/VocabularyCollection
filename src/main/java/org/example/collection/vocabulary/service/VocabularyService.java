package org.example.collection.vocabulary.service;

import jakarta.annotation.Nullable;
import org.example.collection.vocabulary.model.request.VocabularyRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VocabularyService extends CrudService<UUID, VocabularyRequest, VocabularyResponse> {
	VocabularyResponse findById(UUID id);

	PageResponse<VocabularyResponse> findAll(Pageable pageable, UUID vocabularyId, @Nullable String name);

	UUID save(VocabularyRequest request);

	UUID update(VocabularyRequest request);

	void delete(UUID id);
}
