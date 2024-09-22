package org.example.collection.vocabulary.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionUpdateRequest {
	@Size(max = 100)
	@NotEmpty
	private String name;

	@Size(max = 1024)
	private String description;

	private UUID id;
}
