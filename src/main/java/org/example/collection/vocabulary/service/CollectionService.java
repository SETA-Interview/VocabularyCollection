package org.example.collection.vocabulary.service;

import jakarta.annotation.Nullable;
import org.example.collection.vocabulary.model.request.CollectionCreateRequest;
import org.example.collection.vocabulary.model.request.CollectionUpdateRequest;
import org.example.collection.vocabulary.model.response.CollectionResponse;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CollectionService {
	CollectionResponse findById(UUID id);

	PageResponse<CollectionResponse> findAll(Pageable pageable, UUID userId, @Nullable String name);

	UUID save(CollectionCreateRequest request);

	UUID update(CollectionUpdateRequest request);

	void delete(UUID id);
}
