package org.example.collection.vocabulary.controller;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.model.request.VocabularyUpdateRequest;
import org.example.collection.vocabulary.model.request.WordCreateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.example.collection.vocabulary.service.VocabularyService;
import org.example.collection.vocabulary.service.WordService;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("vocabularies")
@RequiredArgsConstructor
public class VocabularyController {
	private final VocabularyService vocabularyService;
	private final WordService wordService;

	@GetMapping("/{vocabulary-id}")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<VocabularyResponse> findById(@PathVariable("vocabulary-id") UUID vocabularyId) {
		return ResponseEntity.ok(vocabularyService.findById(vocabularyId));
	}

	@PutMapping
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<UUID> update(@RequestBody VocabularyUpdateRequest request) {
		vocabularyService.update(request);
		return ResponseEntity.ok().build();
	}


	@DeleteMapping("/{vocabulary-id}")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> deleteById(@PathVariable("vocabulary-id") UUID vocabularyId) {
		vocabularyService.delete(vocabularyId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{vocabulary-id}/words")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<WordResponse>> findAllWordsInVocabulary(@PathVariable("vocabulary-id") UUID vocabularyId,
																			   @RequestParam(name = "content",
																							 required = false) String content,
																			   Pageable pageable) {
		return ResponseEntity.ok(wordService.findAll(pageable, vocabularyId, content));
	}

	@PostMapping("/{vocabulary-id}/words")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<UUID> saveWordInVocabulary(@PathVariable("vocabulary-id") UUID vocabularyId,
													 @RequestParam WordCreateRequest request) {
		return ResponseEntity.ok().body(wordService.save(request, vocabularyId));
	}
}
