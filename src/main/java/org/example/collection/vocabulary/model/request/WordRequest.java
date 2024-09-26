package org.example.collection.vocabulary.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordRequest {
	@Size(max = 1024)
	private String content;

	@Size(max = 128)
	private String description;

	@NotEmpty
	private Locale locale;

	private UUID id;

	@JsonProperty("vocabulary_id")
	private UUID vocabularyId;
}
