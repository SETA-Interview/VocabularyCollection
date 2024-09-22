package org.example.collection.vocabulary.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordCreateRequest {
	private String content;

	private String description;

	private Locale locale;
}
