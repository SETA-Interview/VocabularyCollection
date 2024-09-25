package org.example.collection.vocabulary.controller;

import org.example.collection.vocabulary.model.request.VocabularyRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.VocabularyResponse;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.example.collection.vocabulary.service.VocabularyService;
import org.example.collection.vocabulary.service.WordService;
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
@RequestMapping("vocabularies")
public class VocabularyController extends DefaultCrudController<UUID, VocabularyRequest, VocabularyResponse> {
	private final WordService wordService;

	protected VocabularyController(VocabularyService vocabularyService, WordService wordService) {
		super(vocabularyService);
		this.wordService = wordService;
	}

	@GetMapping("/{vocabulary-id}/words")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<WordResponse>> findAllWordsInVocabulary(@PathVariable("vocabulary-id") UUID vocabularyId,
																			   @RequestParam(name = "content",
																							 required = false) String content,
																			   Pageable pageable) {
		return ResponseEntity.ok(wordService.findAll(pageable, vocabularyId, content));
	}
}
