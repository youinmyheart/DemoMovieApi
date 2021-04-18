package com.demo.movieapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieDetail {
    @SerializedName("id")
    private int id;
    @SerializedName("adult")
    private boolean isAdult;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("belongs_to_collection")
    private MovieCollection movieCollection;
    @SerializedName("budget")
    private int budget;
    @SerializedName("genres")
    private List<GenreResponse.Genre> genres;
    @SerializedName("homepage")
    private String homepage;
    @SerializedName("imdb_id")
    private String imdbId;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    @SerializedName("overview")
    private String overview;
    @SerializedName("popularity")
    private float popularity;
    @SerializedName("production_companies")
    private List<Company> productionCompanies;
    @SerializedName("release_date")
    private String releaseDate;
    @SerializedName("revenue")
    private int revenue;
    @SerializedName("runtime")
    private int runtime;
    @SerializedName("spoken_languages")
    private List<Language> spokenLanguages;
    @SerializedName("status")
    private String status;
    @SerializedName("tagline")
    private String tagLine;
    @SerializedName("title")
    private String title;
    @SerializedName("video")
    private boolean isVideo;
    @SerializedName("vote_count")
    private int voteCount;
    @SerializedName("vote_average")
    private float voteAverage;
    @SerializedName("videos")
    private VideoResponse videoResponse;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public void setAdult(boolean adult) {
        isAdult = adult;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public MovieCollection getMovieCollection() {
        return movieCollection;
    }

    public void setMovieCollection(MovieCollection movieCollection) {
        this.movieCollection = movieCollection;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public List<GenreResponse.Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreResponse.Genre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
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

    public List<Company> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<Company> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public List<Language> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<Language> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTagLine() {
        return tagLine;
    }

    public void setTagLine(String tagLine) {
        this.tagLine = tagLine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public VideoResponse getVideoResponse() {
        return videoResponse;
    }

    public void setVideoResponse(VideoResponse videoResponse) {
        this.videoResponse = videoResponse;
    }

    public static class MovieCollection {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("backdrop_path")
        private String backdropPath;
        @SerializedName("poster_path")
        private String posterPath;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getBackdropPath() {
            return backdropPath;
        }

        public void setBackdropPath(String backdropPath) {
            this.backdropPath = backdropPath;
        }

        public String getPosterPath() {
            return posterPath;
        }

        public void setPosterPath(String posterPath) {
            this.posterPath = posterPath;
        }
    }

    public static class Company {
        @SerializedName("id")
        private int id;
        @SerializedName("name")
        private String name;
        @SerializedName("logo_path")
        private String logoPath;
        @SerializedName("origin_country")
        private String originCountry;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLogoPath() {
            return logoPath;
        }

        public void setLogoPath(String logoPath) {
            this.logoPath = logoPath;
        }

        public String getOriginCountry() {
            return originCountry;
        }

        public void setOriginCountry(String originCountry) {
            this.originCountry = originCountry;
        }
    }

    public static class Country {
        @SerializedName("iso_3166_1")
        private String iso3166;
        @SerializedName("name")
        private String name;

        public String getIso3166() {
            return iso3166;
        }

        public void setIso3166(String iso3166) {
            this.iso3166 = iso3166;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Language {
        @SerializedName("english_name")
        private String englishName;
        @SerializedName("iso_639_1")
        private String iso639;
        @SerializedName("name")
        private String name;

        public String getEnglishName() {
            return englishName;
        }

        public void setEnglishName(String englishName) {
            this.englishName = englishName;
        }

        public String getIso639() {
            return iso639;
        }

        public void setIso639(String iso639) {
            this.iso639 = iso639;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class Video {
        @SerializedName("id")
        private String id;
        @SerializedName("iso_639_1")
        private String iso639;
        @SerializedName("iso_3166_1")
        private String iso3166;
        @SerializedName("key")
        private String key;
        @SerializedName("name")
        private String name;
        @SerializedName("site")
        private String site;
        @SerializedName("size")
        private int size;
        @SerializedName("type")
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIso639() {
            return iso639;
        }

        public void setIso639(String iso639) {
            this.iso639 = iso639;
        }

        public String getIso3166() {
            return iso3166;
        }

        public void setIso3166(String iso3166) {
            this.iso3166 = iso3166;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSite() {
            return site;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class VideoResponse {
        @SerializedName("results")
        private List<Video> videos;

        public List<Video> getVideos() {
            return videos;
        }

        public void setVideos(List<Video> videos) {
            this.videos = videos;
        }
    }
}
