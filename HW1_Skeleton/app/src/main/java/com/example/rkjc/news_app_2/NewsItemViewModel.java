package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {
    private NewsItemRepository mNewsRepository;

    private LiveData<List<NewsItem>> mAllNews;

    public NewsItemViewModel(Application application) {
        super(application);
        mNewsRepository= new NewsItemRepository(application);
        mAllNews = mNewsRepository.getAllNews();
    }


    public LiveData<List<NewsItem>> getAllNews () {return mAllNews;}

}
