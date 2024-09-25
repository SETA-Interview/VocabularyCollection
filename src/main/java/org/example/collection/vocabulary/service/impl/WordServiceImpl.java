package org.example.collection.vocabulary.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.entity.Vocabulary_;
import org.example.collection.vocabulary.entity.Word;
import org.example.collection.vocabulary.entity.Word_;
import org.example.collection.vocabulary.exception.ResourceNotFoundException;
import org.example.collection.vocabulary.model.request.WordRequest;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.example.collection.vocabulary.repository.WordRepository;
import org.example.collection.vocabulary.service.WordService;
import org.example.collection.vocabulary.utils.mapper.WordRequestMapper;
import org.example.collection.vocabulary.utils.mapper.WordResponseMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.example.collection.vocabulary.utils.specification.SpecificationBuilder.equalManyToOne;
import static org.example.collection.vocabulary.utils.specification.SpecificationBuilder.likeIgnoreCase;

@Service
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {
	private final WordRepository wordRepository;

	@Override
	public WordResponse findById(UUID id) {
		return WordResponseMapper.INSTANCE.map(
				wordRepository.findById(id)
							  .orElseThrow(() -> new ResourceNotFoundException("Word with id " + id + "not found ")));
	}

	@Override
	public PageResponse<WordResponse> findAll(Pageable pageable, UUID vocabularyId, String content) {
		return WordResponseMapper.INSTANCE.map(
				wordRepository.findAll(
						equalManyToOne(Word_.vocabulary, Vocabulary_.id, vocabularyId).and(likeIgnoreCase(Word_.content, content)),
						pageable));
	}

	@Override
	public UUID save(WordRequest request) {
		return wordRepository.save(WordRequestMapper.INSTANCE.map(request)).getId();
	}

	@Override
	public UUID update(WordRequest request) {
		Word word = wordRepository.findById(request.getId()).orElseThrow(
				() -> new ResourceNotFoundException("Word with id " + request.getId() + " not found"));
		word.setDescription(request.getDescription());
		word.setContent(request.getContent());
		word.setLocale(request.getLocale());
		return wordRepository.save(word).getId();
	}

	@Override
	public void delete(UUID id) {
		wordRepository.deleteById(id);
	}
}
