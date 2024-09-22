package org.example.collection.vocabulary.repository;

import org.example.collection.vocabulary.entity.Vocabulary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VocabularyRepository extends JpaRepository<Vocabulary, UUID> {
	Page<Vocabulary> findAllByNameContainingIgnoreCaseAndCollectionId(String name, UUID collectionId, Pageable pageable);
	Page<Vocabulary> findAllByCollectionId(UUID collectionId, Pageable pageable);
}
