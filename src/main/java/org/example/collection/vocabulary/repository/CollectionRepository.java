package org.example.collection.vocabulary.repository;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.example.collection.vocabulary.entity.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CollectionRepository extends JpaRepository<Collection, UUID> {
	Page<Collection> findAllByNameContainingIgnoreCaseAndUserId(@Size(max = 100) @NotEmpty String name, String userId, Pageable pageable);
	Page<Collection> findAllByUserId(String userId, Pageable pageable);
}
