package org.example.collection.vocabulary.entity.audit;

import jakarta.annotation.Nonnull;
import org.example.collection.vocabulary.utils.ContextUtils;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.UUID;

public class UserIdAuditorAware implements AuditorAware<UUID> {
	@Override
	@Nonnull
	public Optional<UUID> getCurrentAuditor() {
		return Optional.ofNullable(ContextUtils.extractUserIdFromSecurityContext());
	}
}
