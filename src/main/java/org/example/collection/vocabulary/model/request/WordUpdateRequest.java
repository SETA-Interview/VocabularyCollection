package org.example.collection.vocabulary.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordUpdateRequest {
	private UUID id;

	private String content;

	private String description;

	private Locale locale;
}
