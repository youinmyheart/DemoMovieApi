package com.demo.movieapi.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.demo.movieapi.model.DataWrapper;
import com.demo.movieapi.model.TMDBResponse;
import com.demo.movieapi.repository.TrendingRepo;

public class TrendingViewModel extends ViewModel {
    private TrendingRepo trendingRepo;
    private MutableLiveData<DataWrapper<TMDBResponse>> mutableLiveData;

    private int currentPage = 0;
    private boolean loading = true;
    private int previousTotalItems = 0;

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

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public int getPreviousTotalItems() {
        return previousTotalItems;
    }

    public void setPreviousTotalItems(int previousTotalItems) {
        this.previousTotalItems = previousTotalItems;
    }
}
