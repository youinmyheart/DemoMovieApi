package com.demo.movieapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastCrew {
    @SerializedName("id")
    private int movieId;
    @SerializedName("cast")
    private List<Cast> casts;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public class Cast {
        @SerializedName("adult")
        private boolean isAdult;
        @SerializedName("gender")
        private int gender;
        @SerializedName("id")
        private int id;
        @SerializedName("known_for_department")
        private String knownForDepartment;
        @SerializedName("name")
        private String name;
        @SerializedName("original_name")
        private String originalName;
        @SerializedName("popularity")
        private float popularity;
        @SerializedName("profile_path")
        private String profilePath;
        @SerializedName("cast_id")
        private int castId;
        @SerializedName("character")
        private String character;
        @SerializedName("credit_id")
        private String creditId;
        @SerializedName("order")
        private int order;
        @SerializedName("department")
        private String department;
        @SerializedName("job")
        private String job;

        public boolean isAdult() {
            return isAdult;
        }

        public void setAdult(boolean adult) {
            isAdult = adult;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getKnownForDepartment() {
            return knownForDepartment;
        }

        public void setKnownForDepartment(String knownForDepartment) {
            this.knownForDepartment = knownForDepartment;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOriginalName() {
            return originalName;
        }

        public void setOriginalName(String originalName) {
            this.originalName = originalName;
        }

        public float getPopularity() {
            return popularity;
        }

        public void setPopularity(float popularity) {
            this.popularity = popularity;
        }

        public String getProfilePath() {
            return profilePath;
        }

        public void setProfilePath(String profilePath) {
            this.profilePath = profilePath;
        }

        public int getCastId() {
            return castId;
        }

        public void setCastId(int castId) {
            this.castId = castId;
        }

        public String getCharacter() {
            return character;
        }

        public void setCharacter(String character) {
            this.character = character;
        }

        public String getCreditId() {
            return creditId;
        }

        public void setCreditId(String creditId) {
            this.creditId = creditId;
        }

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }

        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job;
        }
    }
}
