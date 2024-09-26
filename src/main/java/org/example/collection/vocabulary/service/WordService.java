package org.example.collection.vocabulary.service;

import jakarta.annotation.Nullable;
import org.example.collection.vocabulary.model.request.WordRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface WordService extends CrudService<UUID, WordRequest,WordResponse> {
	WordResponse findById(UUID id);

	PageResponse<WordResponse> findAll(Pageable pageable, UUID collectionId, @Nullable String content);

	UUID save(WordRequest request);

	UUID update(WordRequest request);

	void delete(UUID id);
}
