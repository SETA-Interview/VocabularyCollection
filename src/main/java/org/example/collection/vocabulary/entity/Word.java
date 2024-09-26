package org.example.collection.vocabulary.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Locale;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Word {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false)
	private UUID id;

	@Column(name = "name", nullable = false, unique = true, length = 100)
	@Size(max = 100)
	@NotEmpty
	private String content;

	@Column(name = "description", length = 1024)
	@Size(max = 1024)
	private String description;

	@Column(name = "locale", nullable = false)
	@Builder.Default
	private Locale locale = Locale.ENGLISH;

	@ManyToOne
	@JoinColumn(name = "vocabulary_id")
	private Vocabulary vocabulary;
}
