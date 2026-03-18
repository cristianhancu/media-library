package com.student.model;

import jakarta.persistence.*;
import java.io.Serializable;


    @Entity
    @Table(name = "library_entry")
    public class LibraryEntry implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(name = "user_id")
        private String userId;

        @Column(name = "media_item_id")
        private String mediaItemId;

        @Enumerated(EnumType.STRING)
        private LibraryStatus status;

        private Integer rating;

        @Column(name = "added_at")
        private Integer addedAt;

        public LibraryEntry() {}

        public LibraryEntry(Long id, String userId, String mediaItemId, LibraryStatus status, Integer rating, Integer addedAt) {
            this.id = id;
            this.userId = userId;
            this.mediaItemId = mediaItemId;
            this.status = status;
            this.rating = rating;
            this.addedAt = addedAt;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMediaItemId() {
            return mediaItemId;
        }

        public void setMediaItemId(String mediaItemId) {
            this.mediaItemId = mediaItemId;
        }

        public LibraryStatus getStatus() {
            return status;
        }

        public void setStatus(LibraryStatus status) {
            this.status = status;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public Integer getAddedAt() {
            return addedAt;
        }

        public void setAddedAt(Integer addedAt) {
            this.addedAt = addedAt;
        }
    }