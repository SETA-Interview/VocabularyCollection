package org.example.collection.vocabulary.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.entity.Vocabulary;
import org.example.collection.vocabulary.entity.Word;
import org.example.collection.vocabulary.exception.InvalidRequestParamException;
import org.example.collection.vocabulary.model.request.WordCreateRequest;
import org.example.collection.vocabulary.model.request.WordUpdateRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.example.collection.vocabulary.repository.WordRepository;
import org.example.collection.vocabulary.service.WordService;
import org.example.collection.vocabulary.utils.mapper.WordRequestMapper;
import org.example.collection.vocabulary.utils.mapper.WordResponseMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
	private final WordRepository wordRepository;

	@Override
	public WordResponse findById(UUID id) {
		return WordResponseMapper.INSTANCE.map(
				wordRepository.findById(id)
							  .orElseThrow(() -> new InvalidRequestParamException("Word with id " + id + "not found ")));
	}

	@Override
	public PageResponse<WordResponse> findAll(int pageNumber, int pageSize, UUID vocabularyId) {
		return WordResponseMapper.INSTANCE.map(wordRepository.findAllByVocabularyId(vocabularyId, PageRequest.of(pageNumber, pageSize)));
	}

	@Override
	public void save(WordCreateRequest request, UUID vocabularyId) {
		Word word = WordRequestMapper.INSTANCE.map(request);
		word.setVocabulary(Vocabulary.builder().id(vocabularyId).build());
		wordRepository.save(word);
	}

	@Override
	public void update(WordUpdateRequest request) {
		Word word = wordRepository.findById(request.getId()).orElseThrow(
				() -> new InvalidRequestParamException("Word with id " + request.getId() + " not found"));
		word.setDescription(request.getDescription());
		word.setContent(request.getContent());
		word.setLocale(request.getLocale());
		wordRepository.save(word);
	}

	@Override
	public void delete(UUID id) {
		wordRepository.deleteById(id);
	}

	@Override
	public PageResponse<WordResponse> findByContent(int pageNumber, int pageSize, UUID vocabularyId, String content) {
		return WordResponseMapper.INSTANCE.map(
				wordRepository.findAllByVocabularyIdAndContentContainingIgnoreCase(vocabularyId, content, PageRequest.of(pageNumber,
																														 pageSize)));
	}
}
