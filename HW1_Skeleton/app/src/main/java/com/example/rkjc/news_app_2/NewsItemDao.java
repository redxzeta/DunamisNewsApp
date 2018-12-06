package com.example.rkjc.news_app_2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Insert
    void insert (List<NewsItem> items);

    @Query("Delete from news_item")
    void deleteAll();

    @Delete
    void delete(NewsItem newsItem);


    @Query("Select * from news_item")
    LiveData<List<NewsItem>> loadAllNewsItems();


   // void delete(NewsItem param);
}
