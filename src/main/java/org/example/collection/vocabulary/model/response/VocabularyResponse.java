package org.example.collection.vocabulary.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyResponse {
	private UUID id;

	private String name;

	private String description;
}
