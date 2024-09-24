package org.example.collection.vocabulary.configuration;

import org.example.collection.vocabulary.entity.audit.UserIdAuditorAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.UUID;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@Configuration
public class PersistenceConfiguration {

	@Bean
	public AuditorAware<UUID> auditorProvider() {
		return new UserIdAuditorAware();
	}
}
