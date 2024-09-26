package org.example.collection.vocabulary.controller;

import org.springframework.http.ResponseEntity;

@SuppressWarnings("unused")
// This is a controller interface, which means these methods will be called from external
public interface CrudController<ID, CR, RR> {
	ResponseEntity<RR> findById(ID id);

	ResponseEntity<ID> save(CR request);

	ResponseEntity<ID> update(CR request);

	ResponseEntity<Void> deleteById(ID id);
}
