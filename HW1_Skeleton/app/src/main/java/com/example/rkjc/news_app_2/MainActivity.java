package com.example.rkjc.news_app_2;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.example.rkjc.news_app_2.JsonUtils;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private ArrayList<NewsItem> news = new ArrayList<>();


    private RecyclerView mRecyclerView;

    private NewsRecyclerViewAdapter mNewsRecyclerViewAdapter;
    private URL newsUrl =NetworkUtils.buildUrl();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);
        mNewsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, news);
        mRecyclerView.setAdapter(mNewsRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public class NewsQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected String doInBackground(URL... urls) {
            String r= "";
            try {
                r=NetworkUtils.getResponseFromHttpUrl(newsUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return r;
        }
        @Override
        protected void onPostExecute(String newsSearchResults) {


          //  news.clear();
            news = JsonUtils.parseNews(newsSearchResults);
            mNewsRecyclerViewAdapter.mNews.addAll(news);
            mNewsRecyclerViewAdapter.notifyDataSetChanged();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatWasClickedId = item.getItemId();
        if (itemThatWasClickedId == R.id.action_search) {
            //makeNewsSearchQuery();
            new NewsQueryTask().execute();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
