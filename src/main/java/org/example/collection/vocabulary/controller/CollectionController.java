package org.example.collection.vocabulary.controller;

import lombok.RequiredArgsConstructor;
import org.example.collection.vocabulary.model.request.CollectionCreateRequest;
import org.example.collection.vocabulary.model.request.CollectionUpdateRequest;
import org.example.collection.vocabulary.model.response.CollectionResponse;
import org.example.collection.vocabulary.model.response.PageResponse;
import org.example.collection.vocabulary.service.CollectionService;
import org.example.collection.vocabulary.utils.ContextUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CollectionController {
	private final CollectionService collectionService;

	@GetMapping("collections")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<CollectionResponse>> findAll(@RequestParam(name = "page-size") int pageSize,
																	@RequestParam(name = "page-number") int pageNumber) {
		return ResponseEntity.ok(collectionService.findAll(pageNumber, pageSize,
														   ContextUtils.extractUserIdFromSecurityContext()));
	}

	@GetMapping("collections/{collection-id}")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<CollectionResponse> findById(@PathVariable(name = "collection-id") UUID collectionId) {
		return ResponseEntity.ok(collectionService.findById(collectionId));
	}

	@PostMapping("collections")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> save(@RequestBody CollectionCreateRequest request) {
		collectionService.save(request, ContextUtils.extractUserIdFromSecurityContext());
		return ResponseEntity.ok().build();
	}

	@PutMapping("collections")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> update(@RequestBody CollectionUpdateRequest request) {
		collectionService.update(request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("collections/{collection-id}")
	@PreAuthorize("hasAnyAuthority('Admin')")
	public ResponseEntity<Void> deleteById(@PathVariable(name = "collection-id") UUID collectionId) {
		collectionService.delete(collectionId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("collections/name")
	@PreAuthorize("hasAnyAuthority('Viewer', 'Admin')")
	public ResponseEntity<PageResponse<CollectionResponse>> findByName(@RequestParam(name = "page-size") int pageSize,
															   @RequestParam(name = "page-number") int pageNumber,
															   @RequestParam(name = "name") String name) {
		return ResponseEntity.ok(collectionService.findByName(pageNumber, pageSize, ContextUtils.extractUserIdFromSecurityContext(), name));
	}
}
