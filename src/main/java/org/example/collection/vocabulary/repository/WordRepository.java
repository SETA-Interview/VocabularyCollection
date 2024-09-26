package org.example.collection.vocabulary.repository;

import org.example.collection.vocabulary.entity.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface WordRepository extends JpaRepository<Word, UUID>, JpaSpecificationExecutor<Word> {

}
