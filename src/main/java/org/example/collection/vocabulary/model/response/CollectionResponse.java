package org.example.collection.vocabulary.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionResponse {
	private UUID id;

	private String name;

	private String description;

	@JsonProperty("user_id")
	private String userId;
}
