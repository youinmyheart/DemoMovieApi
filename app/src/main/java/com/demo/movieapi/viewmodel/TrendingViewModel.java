package com.demo.movieapi.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.TMDBResponse;
import com.demo.movieapi.repository.TrendingRepo;

public class TrendingViewModel extends ViewModel {
    private TrendingRepo trendingRepo;
    private MutableLiveData<DataWrapper<TMDBResponse>> mutableLiveData;

    public TrendingViewModel() {
        trendingRepo = new TrendingRepo();
    }

    public MutableLiveData<DataWrapper<TMDBResponse>> getMutableLiveData() {
        return mutableLiveData;
    }

    public MutableLiveData<DataWrapper<TMDBResponse>> getTrending(int page) {
        mutableLiveData = trendingRepo.getTrending(page);
        return mutableLiveData;
    }
}
