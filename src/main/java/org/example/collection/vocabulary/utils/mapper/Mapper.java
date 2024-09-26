package org.example.collection.vocabulary.utils.mapper;

import org.example.collection.vocabulary.model.response.PageResponse;
import org.springframework.data.domain.Page;

import java.util.Collection;

public interface Mapper<S, D> {
	D map(S source);

	default PageResponse<D> map(Page<S> source) {
		return new PageResponse<>(source.getContent().stream().map(this::map).toList(),
								  source.getPageable().getPageNumber(),
								  source.getPageable().getPageSize(),
								  source.getTotalElements());
	}

	default Collection<D> map(Collection<S> source) {
		return source.stream().map(this::map).toList();
	}
}
