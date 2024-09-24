package org.example.collection.vocabulary.repository;

import org.example.collection.vocabulary.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CollectionRepository extends JpaRepository<Collection, UUID>, JpaSpecificationExecutor<Collection> {
}
