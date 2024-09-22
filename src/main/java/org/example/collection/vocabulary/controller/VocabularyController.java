package org.example.collection.vocabulary.controller;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.model.request.VocabularyCreateRequest;
import org.example.collection.vocabulary.model.request.VocabularyUpdateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.example.collection.vocabulary.service.VocabularyService;
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
@RequestMapping
@RequiredArgsConstructor
public class VocabularyController {
	private final VocabularyService vocabularyService;

	@GetMapping("collections/{collection-id}/vocabularies")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<VocabularyResponse>> findAll(@PathVariable("collection-id") UUID collectionId,
																	@RequestParam(name = "page-size") int pageSize,
																	@RequestParam(name = "page-number") int pageNumber) {
		return ResponseEntity.ok(vocabularyService.findAll(pageNumber, pageSize, collectionId));
	}

	@GetMapping("vocabularies/{vocabulary-id}")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<VocabularyResponse> findById(@PathVariable("vocabulary-id") UUID vocabularyId) {
		return ResponseEntity.ok(vocabularyService.findById(vocabularyId));
	}

	@PostMapping("collections/{collection-id}/vocabularies")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> save(@PathVariable("collection-id") UUID collectionId, @RequestBody VocabularyCreateRequest request) {
		vocabularyService.save(request, collectionId);
		return ResponseEntity.ok().build();
	}

	@PutMapping("vocabularies")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> update(@RequestBody VocabularyUpdateRequest request) {
		vocabularyService.update(request);
		return ResponseEntity.ok().build();
	}


	@DeleteMapping("vocabularies/{vocabulary-id}")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> deleteById(@PathVariable("vocabulary-id") UUID vocabularyId) {
		vocabularyService.delete(vocabularyId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("collections/{collection-id}/vocabularies/name")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<VocabularyResponse>> findByName(@PathVariable("collection-id") UUID collectionId,
															   @RequestParam(name = "page-size") int pageSize,
															   @RequestParam(name = "page-number") int pageNumber,
															   @RequestParam(name = "name") String name) {
		return ResponseEntity.ok(vocabularyService.findByName(pageNumber, pageSize, name, collectionId));
	}
}
