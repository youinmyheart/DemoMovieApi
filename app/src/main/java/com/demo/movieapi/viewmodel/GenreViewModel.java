package com.demo.movieapi.viewmodel;

import androidx.lifecycle.MutableLiveData;

import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.GenreResponse;
import com.demo.movieapi.repository.GenreRepo;

public class GenreViewModel {

    private GenreRepo genreRepo;
    private MutableLiveData<DataWrapper<GenreResponse>> mutableLiveData;

    public GenreViewModel() {
        genreRepo = new GenreRepo();
    }

    public MutableLiveData<DataWrapper<GenreResponse>> getGenre() {
        mutableLiveData = genreRepo.getGenre();
        return mutableLiveData;
    }
}
