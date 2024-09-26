package org.example.collection.vocabulary.repository;

import org.example.collection.vocabulary.entity.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface VocabularyRepository extends JpaRepository<Vocabulary, UUID>, JpaSpecificationExecutor<Vocabulary> {
}
