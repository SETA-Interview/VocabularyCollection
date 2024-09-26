package org.example.collection.vocabulary.utils.specification;

import jakarta.annotation.Nullable;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class SpecificationBuilder {

	public static <T> Specification<T> likeIgnoreCase(SingularAttribute<T, String> path, @Nullable String str) {
		return (root, query, builder) -> {
			if (!StringUtils.hasText(str)) {
				return builder.conjunction();
			}
			return builder.like(builder.lower(root.get(path)), "%" + str.toLowerCase() + "%");
		};
	}

	public static <T, E> Specification<T> equal(SingularAttribute<T, E> field, @Nullable E e) {
		return (root, query, builder) -> {
			if (e == null) {
				return builder.conjunction();
			}
			return builder.equal(root.get(field), e);
		};
	}

	public static <T, E, X> Specification<T> equalManyToOne(SingularAttribute<T, E> path, SingularAttribute<E, ?> idField, @Nullable X e) {
		return (root, query, builder) -> {
			if (e == null) {
				return builder.conjunction();
			}

			return builder.equal(root.get(path).get(idField), e);
		};
	}
}
