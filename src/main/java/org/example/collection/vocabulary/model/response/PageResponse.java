package org.example.collection.vocabulary.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResponse<T> {
	private List<T> content;
	@JsonProperty("page_number")
	private int pageNumber;
	@JsonProperty("page_size")
	private int pageSize;
	private long total;
}
