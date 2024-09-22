package org.example.collection.vocabulary.repository;

import org.example.collection.vocabulary.entity.Word;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WordRepository extends JpaRepository<Word, UUID> {
	Page<Word> findAllByVocabularyId(UUID vocabularyId, Pageable pageable);

	Page<Word> findAllByVocabularyIdAndContentContainingIgnoreCase(UUID vocabularyId, String content, Pageable pageable);
}
