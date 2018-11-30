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

    public void delete(NewsItem newsitem){
        new deleteAsyncTask(mNewsItemDao).execute(newsitem);
    }

    public void pop(NewsItem newsitem) {
        new popAsyncTask(mNewsItemDao).execute(newsitem);
    }

    public LiveData<List<NewsItem>> getAllNews(){
        return mNewsItems;
    }
    public void getAll (NewsItem newsitem) {
        new getAllAsyncTask(mNewsItemDao).execute(newsitem);
    }
    private static class getAllAsyncTask extends AsyncTask<NewsItem, Void, Void> {
        private NewsItemDao m;
        private LiveData<List<NewsItem>> mNewsItems;
        private getAllAsyncTask(NewsItemDao dao) {
            m=dao;
        }

        @Override
        protected Void doInBackground(final NewsItem... params) {
            m.loadAllNewsItems();
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
            //Log.d("mycode", "deleteding word: " + params[0].getWord());
            mAsyncNewsDao.deleteAll();



            return null;
        }
    }

    private static class popAsyncTask extends AsyncTask<NewsItem, Void, Void> {
        ArrayList<NewsItem> xNewsItems = new ArrayList<>();
        String x ="";
        private URL newsUrl =NetworkUtils.buildUrl();
        private NewsItemDao  mAsyncNewsDao;
        popAsyncTask(NewsItemDao dao) {
            mAsyncNewsDao = dao;
        }

        @Override
        protected Void doInBackground(final NewsItem... params) {
            String r= "";
            try {
                r=NetworkUtils.getResponseFromHttpUrl(newsUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }


            xNewsItems =JsonUtils.parseNews(x);
            mAsyncNewsDao.insert(xNewsItems);



            return null;
        }
    }
}
