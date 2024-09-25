package org.example.collection.vocabulary.controller;

import org.example.collection.vocabulary.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class DefaultCrudController<ID, CR, RR> implements CrudController<ID, CR, RR> {
	protected final CrudService<ID, CR, RR> service;

	protected DefaultCrudController(CrudService<ID, CR, RR> service) {
		this.service = service;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	@GetMapping("/{id}")
	public ResponseEntity<RR> findById(@PathVariable ID id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@Override
	@PreAuthorize("hasAnyAuthority('Admin')")
	@PostMapping
	public ResponseEntity<ID> save(CR request) {
		return ResponseEntity.ok(service.save(request));
	}

	@Override
	@PreAuthorize("hasAnyAuthority('Admin')")
	@PutMapping
	public ResponseEntity<ID> update(CR request) {
		return ResponseEntity.ok(service.update(request));
	}

	@Override
	@PreAuthorize("hasAnyAuthority('Admin')")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable ID id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
