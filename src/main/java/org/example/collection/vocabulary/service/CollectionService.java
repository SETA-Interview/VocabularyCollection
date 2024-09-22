package org.example.collection.vocabulary.service;

import org.example.collection.vocabulary.model.request.CollectionCreateRequest;
import org.example.collection.vocabulary.model.request.CollectionUpdateRequest;
import org.example.collection.vocabulary.model.response.CollectionResponse;
import org.example.collection.vocabulary.model.response.PageResponse;

import java.util.UUID;

public interface CollectionService {
	CollectionResponse findById(UUID id);

	PageResponse<CollectionResponse> findAll(int pageNumber, int pageSize, UUID userId);

	void save(CollectionCreateRequest request, UUID userId);

	void update(CollectionUpdateRequest request);

	void delete(UUID id);

	PageResponse<CollectionResponse> findByName(int pageNumber, int pageSize, UUID userId, String name);
}
