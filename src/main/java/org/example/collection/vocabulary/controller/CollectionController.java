package org.example.collection.vocabulary.controller;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.model.request.CollectionCreateRequest;
import org.example.collection.vocabulary.model.request.CollectionUpdateRequest;
import org.example.collection.vocabulary.model.request.VocabularyCreateRequest;
import org.example.collection.vocabulary.model.response.CollectionResponse;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.example.collection.vocabulary.service.CollectionService;
import org.example.collection.vocabulary.service.VocabularyService;
import org.example.collection.vocabulary.utils.ContextUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("collections")
@RequiredArgsConstructor
public class CollectionController {
	private final CollectionService collectionService;
	private final VocabularyService vocabularyService;

	@GetMapping
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<CollectionResponse>> findAll(@RequestParam(name = "page-size") int pageSize,
																	@RequestParam(name = "page-number") int pageNumber) {
		return ResponseEntity.ok(collectionService.findAll(pageNumber, pageSize,
														   ContextUtils.extractUserIdFromSecurityContext()));
	}

	@GetMapping("/{collection-id}")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<CollectionResponse> findById(@PathVariable(name = "collection-id") UUID collectionId) {
		return ResponseEntity.ok(collectionService.findById(collectionId));
	}

	@PostMapping
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<UUID> save(@RequestBody CollectionCreateRequest request) {
		return ResponseEntity.ok().body(collectionService.save(request));
	}

	@PutMapping
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<UUID> update(@RequestBody CollectionUpdateRequest request) {
		return ResponseEntity.ok().body(collectionService.update(request));
	}

	@DeleteMapping("/{collection-id}")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "collection-id") UUID collectionId) {
		collectionService.delete(collectionId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/name")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<CollectionResponse>> findByName(@RequestParam(name = "page-size") int pageSize,
																	   @RequestParam(name = "page-number") int pageNumber,
																	   @RequestParam(name = "name") String name) {
		return ResponseEntity.ok(collectionService.findByName(pageNumber, pageSize, ContextUtils.extractUserIdFromSecurityContext(), name));
	}

	@PostMapping("/{collection-id}/vocabularies")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<UUID> saveVocabularyInCollection(@PathVariable("collection-id") UUID collectionId,
														   @RequestBody VocabularyCreateRequest request) {
		return ResponseEntity.ok().body(vocabularyService.save(request, collectionId));
	}

	@GetMapping("/{collection-id}/vocabularies/name")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<VocabularyResponse>> findAllVocabulariesInCollectionByName(
			@PathVariable("collection-id") UUID collectionId,
			@RequestParam(name = "page-size") int pageSize,
			@RequestParam(name = "page-number") int pageNumber,
			@RequestParam(name = "name") String name) {
		return ResponseEntity.ok(vocabularyService.findByName(pageNumber, pageSize, name, collectionId));
	}

	@GetMapping("/{collection-id}/vocabularies")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<VocabularyResponse>> findAllVocabulariesInCollection(
			@PathVariable("collection-id") UUID collectionId,
			@RequestParam(name = "page-size") int pageSize,
			@RequestParam(name = "page-number") int pageNumber) {
		return ResponseEntity.ok(vocabularyService.findAll(pageNumber, pageSize, collectionId));
	}
}
