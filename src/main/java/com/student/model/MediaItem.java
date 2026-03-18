package com.student.model;

import jakarta.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "media_item")
public class MediaItem implements Serializable {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;

		private String title;

		@Enumerated(EnumType.STRING)
		private MediaType type;

		@Column(name = "release_year")
		private int releaseYear;

		private String genre;

		public MediaItem() {}

		public MediaItem(Long id, String title, MediaType type, int releaseYear, String genre) {
			this.id = id;
			this.title = title;
			this.type = type;
			this.releaseYear = releaseYear;
			this.genre = genre;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public MediaType getType() {
			return type;
		}

		public void setType(MediaType type) {
			this.type = type;
		}

		public int getReleaseYear() {
			return releaseYear;
		}

		public void setReleaseYear(int releaseYear) {
			this.releaseYear = releaseYear;
		}

		public String getGenre() {
			return genre;
		}

		public void setGenre(String genre) {
			this.genre = genre;
		}
	}
