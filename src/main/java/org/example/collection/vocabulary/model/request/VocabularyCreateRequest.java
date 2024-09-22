package org.example.collection.vocabulary.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VocabularyCreateRequest {
	@Size(max = 100)
	@NotEmpty
	private String name;

	@Size(max = 1024)
	private String description;
}
