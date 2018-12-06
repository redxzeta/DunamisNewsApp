package com.example.rkjc.news_app_2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsItemRepository {
    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mNewsItems;

    public NewsItemRepository (Application application) {
        NewsItemDatabase db =NewsItemDatabase.getDatabase(application.getApplicationContext());
        mNewsItemDao = db.newsitemDao();
        mNewsItems = mNewsItemDao.loadAllNewsItems();
    }

    LiveData<List<NewsItem>>  loadAllNewsItems() {return mNewsItems;}

    public void deleteAll(){
        new deleteAsyncTask(mNewsItemDao).execute();
    }



    public void insert() {
        new insertAsyncTask(mNewsItemDao).execute();
    }


    private static class insertAsyncTask extends AsyncTask<NewsItem, Void, Void> {
        private NewsItemDao  mAsyncNewsDao;
        private LiveData<List<NewsItem>> mNewsItems;
        private insertAsyncTask(NewsItemDao dao) {
            mAsyncNewsDao=dao;
        }

        @Override
        protected Void doInBackground(final NewsItem... params) {
            mAsyncNewsDao.deleteAll();

            try {
                String dataString = NetworkUtils.getResponseFromHttpUrl(NetworkUtils.buildUrl());
                mAsyncNewsDao.insert(JsonUtils.parseNews(dataString));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<NewsItem, Void, Void> {
        private NewsItemDao  mAsyncNewsDao;
        deleteAsyncTask(NewsItemDao dao) {
            mAsyncNewsDao = dao;
        }

        @Override
        protected Void doInBackground(final NewsItem... params) {
             //URL newsUrl =NetworkUtils.buildUrl();
            Log.d("mycode", "deleteding word: " + params[0].getId());

            mAsyncNewsDao.deleteAll();



            return null;
        }
    }


}
