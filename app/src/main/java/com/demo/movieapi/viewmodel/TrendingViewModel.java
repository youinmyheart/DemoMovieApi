package com.demo.movieapi.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.movieapi.model.TMDBResponse;
import com.demo.movieapi.repository.TrendingRepo;

import java.util.List;

public class TrendingViewModel extends ViewModel {
    private TrendingRepo trendingRepo;
    private MutableLiveData<List<TMDBResponse.Movie>> mutableLiveData;

    public TrendingViewModel() {
        trendingRepo = new TrendingRepo();
    }

    public MutableLiveData<List<TMDBResponse.Movie>> getMutableLiveData() {
        return mutableLiveData;
    }

    public MutableLiveData<List<TMDBResponse.Movie>> getTrending(int page) {
        mutableLiveData = trendingRepo.getTrending(page);
        return mutableLiveData;
    }
}
