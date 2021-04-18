package com.demo.movieapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TMDBResponse {
    @SerializedName("page")
    private int page;
    @SerializedName("total_pages")
    private int totalPages;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("results")
    private List<Movie> movies;
    @SerializedName("dates")
    private MovieDate movieDate;

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

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public MovieDate getMovieDate() {
        return movieDate;
    }

    public void setMovieDate(MovieDate movieDate) {
        this.movieDate = movieDate;
    }

    public static class Movie {
        @SerializedName("id")
        private int id;
        @SerializedName("poster_path")
        private String posterPath;
        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("adult")
        private boolean isAdult;
        @SerializedName("video")
        private boolean isVideo;
        @SerializedName("vote_count")
        private int voteCount;
        @SerializedName("vote_average")
        private float voteAverage;
        @SerializedName("title")
        private String title;
        @SerializedName("original_title")
        private String originalTitle;
        @SerializedName("release_date")
        private String releaseDate;
        @SerializedName("original_language")
        private String originalLanguage;
        @SerializedName("overview")
        private String overview;
        @SerializedName("popularity")
        private float popularity;
        @SerializedName("media_type")
        private String mediaType;
        @SerializedName("genre_ids")
        private List<Integer> genreIds;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public boolean isAdult() {
            return isAdult;
        }

        public void setAdult(boolean adult) {
            isAdult = adult;
        }

        public boolean isVideo() {
            return isVideo;
        }

        public void setVideo(boolean video) {
            isVideo = video;
        }

        public int getVoteCount() {
            return voteCount;
        }

        public void setVoteCount(int voteCount) {
            this.voteCount = voteCount;
        }

        public float getVoteAverage() {
            return voteAverage;
        }

        public void setVoteAverage(float voteAverage) {
            this.voteAverage = voteAverage;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOriginalTitle() {
            return originalTitle;
        }

        public void setOriginalTitle(String originalTitle) {
            this.originalTitle = originalTitle;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public void setReleaseDate(String releaseDate) {
            this.releaseDate = releaseDate;
        }

        public String getOriginalLanguage() {
            return originalLanguage;
        }

        public void setOriginalLanguage(String originalLanguage) {
            this.originalLanguage = originalLanguage;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public float getPopularity() {
            return popularity;
        }

        public void setPopularity(float popularity) {
            this.popularity = popularity;
        }

        public String getMediaType() {
            return mediaType;
        }

        public void setMediaType(String mediaType) {
            this.mediaType = mediaType;
        }

        public List<Integer> getGenreIds() {
            return genreIds;
        }

        public void setGenreIds(List<Integer> genreIds) {
            this.genreIds = genreIds;
        }
    }

    public static class MovieDate {
        @SerializedName("maximum")
        private String maximum;
        @SerializedName("minimum")
        private String minimum;

        public String getMaximum() {
            return maximum;
        }

        public void setMaximum(String maximum) {
            this.maximum = maximum;
        }

        public String getMinimum() {
            return minimum;
        }

        public void setMinimum(String minimum) {
            this.minimum = minimum;
        }
    }
}
