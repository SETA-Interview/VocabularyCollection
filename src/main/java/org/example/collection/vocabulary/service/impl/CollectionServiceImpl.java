package org.example.collection.vocabulary.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.entity.Collection;
import org.example.collection.vocabulary.entity.Collection_;
import org.example.collection.vocabulary.exception.ResourceNotFoundException;
import org.example.collection.vocabulary.model.request.CollectionRequest;
import org.example.collection.vocabulary.model.response.CollectionResponse;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.repository.CollectionRepository;
import org.example.collection.vocabulary.service.CollectionService;
import org.example.collection.vocabulary.utils.mapper.CollectionRequestMapper;
import org.example.collection.vocabulary.utils.mapper.CollectionResponseMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.example.collection.vocabulary.utils.specification.SpecificationBuilder.equal;
import static org.example.collection.vocabulary.utils.specification.SpecificationBuilder.likeIgnoreCase;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {
	private final CollectionRepository collectionRepository;

	@Override
	public CollectionResponse findById(UUID id) {
		return CollectionResponseMapper.INSTANCE.map(
				collectionRepository.findById(id)
									.orElseThrow(() -> new ResourceNotFoundException("Collection with id " + id + "not found ")));
	}

	@Override
	public PageResponse<CollectionResponse> findAll(Pageable pageable, UUID userId, String name) {
		return CollectionResponseMapper.INSTANCE.map(
				collectionRepository.findAll(equal(Collection_.userId, userId).and(likeIgnoreCase(Collection_.name, name)), pageable));
	}

	@Override
	@Transactional
	public UUID save(CollectionRequest request) {
		Collection collection = CollectionRequestMapper.INSTANCE.map(request);
		return collectionRepository.save(collection).getId();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public UUID update(CollectionRequest request) {
		Collection collection = collectionRepository.findById(request.getId())
													.orElseThrow(() -> new ResourceNotFoundException(
															"Collection with id " + request.getId() + "not found "));

		collection.setName(request.getName());
		collection.setDescription(request.getDescription());
		return collectionRepository.save(collection).getId();
	}

	@Override
	public void delete(UUID id) {
		collectionRepository.deleteById(id);
	}
}
