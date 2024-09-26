package org.example.collection.vocabulary.controller;

import org.example.collection.vocabulary.model.request.CollectionRequest;
import org.example.collection.vocabulary.model.response.CollectionResponse;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.example.collection.vocabulary.service.CollectionService;
import org.example.collection.vocabulary.service.VocabularyService;
import org.example.collection.vocabulary.utils.ContextUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("collections")
public class CollectionController extends DefaultCrudController<UUID, CollectionRequest, CollectionResponse> {
	private final CollectionService collectionService;
	private final VocabularyService vocabularyService;

	public CollectionController(CollectionService service, VocabularyService vocabularyService) {
		super(service);
		this.collectionService = service;
		this.vocabularyService = vocabularyService;
	}

	@GetMapping
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<CollectionResponse>> findAll(@RequestParam(name = "name", required = false) String name,
																	Pageable pageable) {
		return ResponseEntity.ok(collectionService.findAll(pageable, ContextUtils.extractUserIdFromSecurityContext(), name));
	}

	@GetMapping("/{collection-id}/vocabularies")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<VocabularyResponse>> findAllVocabulariesInCollection(
			@PathVariable("collection-id") UUID collectionId, @RequestParam(name = "name", required = false) String name,
			Pageable pageable) {
		return ResponseEntity.ok(vocabularyService.findAll(pageable, collectionId, name));
	}
}
