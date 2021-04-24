package com.demo.movieapi.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.movieapi.model.CastCrew;
import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.repository.CastRepo;

public class CastViewModel extends ViewModel {
    private CastRepo castRepo;
    private MutableLiveData<DataWrapper<CastCrew>> mutableLiveData;

    public CastViewModel() {
        castRepo = new CastRepo();
    }

    public MutableLiveData<DataWrapper<CastCrew>> getMovieCast(int movieId) {
        mutableLiveData = castRepo.getMovieCast(movieId);
        return mutableLiveData;
    }

    public MutableLiveData<DataWrapper<CastCrew>> getMutableLiveData() {
        return mutableLiveData;
    }
}
