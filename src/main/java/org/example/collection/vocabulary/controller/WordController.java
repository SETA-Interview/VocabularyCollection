package org.example.collection.vocabulary.controller;

import org.example.collection.vocabulary.model.request.WordRequest;
import org.example.collection.vocabulary.model.response.WordResponse;
import org.example.collection.vocabulary.service.WordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("words")
public class WordController extends DefaultCrudController<UUID, WordRequest, WordResponse> {
	protected WordController(WordService service) {
		super(service);
	}
}
