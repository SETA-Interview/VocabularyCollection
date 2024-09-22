package org.example.collection.vocabulary.controller;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.model.request.WordCreateRequest;
import org.example.collection.vocabulary.model.request.WordUpdateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.example.collection.vocabulary.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class WordController {
	private final WordService wordService;

	@GetMapping("vocabularies/{vocabulary-id}/words")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<WordResponse>> findAll(@PathVariable("vocabulary-id") UUID vocabularyId,
															  @RequestParam(name = "page-size") int pageSize,
															  @RequestParam(name = "page-number") int pageNumber) {
		return ResponseEntity.ok(wordService.findAll(pageNumber, pageSize, vocabularyId));
	}

	@GetMapping("words/{word-id}")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<WordResponse> findById(@PathVariable("word-id") UUID wordId) {
		return ResponseEntity.ok(wordService.findById(wordId));
	}

	@PostMapping("vocabularies/{vocabulary-id}/words")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> save(@PathVariable("vocabulary-id") UUID vocabularyId, @RequestParam WordCreateRequest request) {
		wordService.save(request, vocabularyId);
		return ResponseEntity.ok().build();
	}

	@PutMapping("words")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> update(@RequestParam WordUpdateRequest request) {
		wordService.update(request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("words/{word-id}")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> update(@PathVariable("word-id") UUID wordId) {
		wordService.delete(wordId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("vocabularies/{vocabulary-id}/words/content")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<WordResponse>> findByContent(@PathVariable("vocabulary-id") UUID vocabularyId,
															@RequestParam(name = "page-size") int pageSize,
															@RequestParam(name = "page-number") int pageNumber,
															@RequestParam(name = "content") String content) {
		return ResponseEntity.ok(wordService.findByContent(pageNumber, pageSize, vocabularyId, content));
	}
}
