package org.example.collection.vocabulary.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.entity.Collection;
import org.example.collection.vocabulary.exception.InvalidRequestParamException;
import org.example.collection.vocabulary.model.request.CollectionCreateRequest;
import org.example.collection.vocabulary.model.request.CollectionUpdateRequest;
import org.example.collection.vocabulary.model.response.CollectionResponse;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.repository.CollectionRepository;
import org.example.collection.vocabulary.service.CollectionService;
import org.example.collection.vocabulary.utils.mapper.CollectionRequestMapper;
import org.example.collection.vocabulary.utils.mapper.CollectionResponseMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {
	private final CollectionRepository collectionRepository;

	@Override
	public CollectionResponse findById(UUID id) {
		return CollectionResponseMapper.INSTANCE.map(
				collectionRepository.findById(id)
									.orElseThrow(() -> new InvalidRequestParamException("Collection with id " + id + "not found ")));
	}

	@Override
	public PageResponse<CollectionResponse> findAll(int pageNumber, int pageSize, UUID userId) {
		return CollectionResponseMapper.INSTANCE.map(collectionRepository.findAllByUserId(userId.toString(), PageRequest.of(pageNumber,
																															pageSize)));
	}

	@Override
	@Transactional
	public void save(CollectionCreateRequest request, UUID userId) {
		Collection collection = CollectionRequestMapper.INSTANCE.map(request);
		collection.setUserId(userId.toString());
		collectionRepository.save(collection);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(CollectionUpdateRequest request) {
		Collection collection = collectionRepository.findById(request.getId())
													.orElseThrow(() -> new InvalidRequestParamException(
															"Collection with id " + request.getId() + "not found "));

		collection.setName(request.getName());
		collection.setDescription(request.getDescription());
		collectionRepository.save(collection);
	}

	@Override
	public void delete(UUID id) {
		collectionRepository.deleteById(id);
	}

	@Override
	public PageResponse<CollectionResponse> findByName(int pageNumber, int pageSize, UUID userId, String name) {
		return CollectionResponseMapper.INSTANCE.map(
				collectionRepository.findAllByNameContainingIgnoreCaseAndUserId(name, userId.toString(), PageRequest.of(pageNumber,
																														pageSize)));
	}
}
