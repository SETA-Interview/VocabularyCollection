package org.example.collection.vocabulary.service;

import jakarta.annotation.Nullable;
import org.example.collection.vocabulary.model.request.CollectionRequest;
import org.example.collection.vocabulary.model.response.CollectionResponse;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CollectionService extends CrudService<UUID, CollectionRequest, CollectionResponse> {
	CollectionResponse findById(UUID id);

	PageResponse<CollectionResponse> findAll(Pageable pageable, UUID userId, @Nullable String name);

	UUID save(CollectionRequest request);

	UUID update(CollectionRequest request);

	void delete(UUID id);
}
