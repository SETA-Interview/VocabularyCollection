package org.example.collection.vocabulary.controller;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.model.request.WordUpdateRequest;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.example.collection.vocabulary.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("words")
@RequiredArgsConstructor
public class WordController {
	private final WordService wordService;

	@GetMapping("/{word-id}")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<WordResponse> findById(@PathVariable("word-id") UUID wordId) {
		return ResponseEntity.ok(wordService.findById(wordId));
	}

	@PutMapping
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<UUID> update(@RequestParam WordUpdateRequest request) {
		return ResponseEntity.ok().body(wordService.update(request));
	}

	@DeleteMapping("/{word-id}")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> deleteById(@PathVariable("word-id") UUID wordId) {
		wordService.delete(wordId);
		return ResponseEntity.ok().build();
	}
}
