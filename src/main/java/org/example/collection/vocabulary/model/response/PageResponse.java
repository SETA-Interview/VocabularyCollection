package org.example.collection.vocabulary.model.response;

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
	private int pageNumber;
	private int pageSize;
	private long total;
}
