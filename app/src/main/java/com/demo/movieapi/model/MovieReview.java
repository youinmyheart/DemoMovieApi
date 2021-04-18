package com.demo.movieapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieReview {
    @SerializedName("id")
    private int movieId;
    @SerializedName("page")
    private int page;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("results")
    private List<ReviewDetail> reviewDetails;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<ReviewDetail> getReviewDetails() {
        return reviewDetails;
    }

    public void setReviewDetails(List<ReviewDetail> reviewDetails) {
        this.reviewDetails = reviewDetails;
    }

    public static class ReviewDetail {
        @SerializedName("id")
        private String id;
        @SerializedName("author")
        private String author;
        @SerializedName("author_details")
        private AuthorDetail authorDetail;
        @SerializedName("content")
        private String content;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("url")
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public AuthorDetail getAuthorDetail() {
            return authorDetail;
        }

        public void setAuthorDetail(AuthorDetail authorDetail) {
            this.authorDetail = authorDetail;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public static class AuthorDetail {
        @SerializedName("name")
        private String name;
        @SerializedName("username")
        private String username;
        @SerializedName("avatar_path")
        private String avatarPath;
        @SerializedName("rating")
        private int rating;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAvatarPath() {
            return avatarPath;
        }

        public void setAvatarPath(String avatarPath) {
            this.avatarPath = avatarPath;
        }

        public int getRating() {
            return rating;
        }

        public void setRating(int rating) {
            this.rating = rating;
        }
    }
}
