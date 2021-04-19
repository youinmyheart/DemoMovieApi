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

    public MutableLiveData<List<TMDBResponse.Movie>> getTrending() {
        if (mutableLiveData == null) {
            mutableLiveData = trendingRepo.getTrending();
        }
        return mutableLiveData;
    }
}
