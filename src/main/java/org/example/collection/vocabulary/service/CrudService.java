package org.example.collection.vocabulary.service;

import jakarta.annotation.Nullable;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.springframework.data.domain.Pageable;

public interface CrudService<ID, CR, RR> {
	RR findById(ID id);

	PageResponse<RR> findAll(Pageable pageable, ID filterId, @Nullable String extraField);

	ID save(CR request);

	ID update(CR request);

	void delete(ID id);
}
