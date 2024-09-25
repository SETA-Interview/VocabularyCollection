package org.example.collection.vocabulary.controller;

import org.example.collection.vocabulary.service.CrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

public class DefaultCrudController<ID, CR, RR> implements CrudController<ID, CR, RR> {
	protected final CrudService<ID, CR, RR> service;

	protected DefaultCrudController(CrudService<ID, CR, RR> service) {
		this.service = service;
	}

	@Override
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<RR> findById(ID id) {
		return ResponseEntity.ok(service.findById(id));
	}

	@Override
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<ID> save(CR request) {
		return ResponseEntity.ok(service.save(request));
	}

	@Override
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<ID> update(CR request) {
		return ResponseEntity.ok(service.update(request));
	}

	@Override
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> deleteById(ID id) {
		service.delete(id);
		return ResponseEntity.ok().build();
	}
}
