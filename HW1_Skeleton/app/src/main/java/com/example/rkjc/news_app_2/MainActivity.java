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
import java.net.URL;
import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {
    private ArrayList<NewsItem> news = new ArrayList<>();
    private TextView mSearchResultsTextView;
    private static final String SEARCH_QUERY_RESULTS = "searchResults";
    private RecyclerView mRecyclerView;
    private String gitHubSearchResults;
    private NewsRecyclerViewAdapter mNewsRecyclerViewAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mSearchResultsTextView = (TextView) findViewById(R.id.tv_news_search_results_json);
        mRecyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);
        mNewsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, news);
        mRecyclerView.setAdapter(mNewsRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        String searchResults = savedInstanceState.getString(SEARCH_QUERY_RESULTS);
        populateRecyclerView(searchResults);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SEARCH_QUERY_RESULTS, gitHubSearchResults);
    }

        private void makeNewsSearchQuery(){
        String yay= "A";
        URL SearchUrl = NetworkUtils.buildUrl();
        new NewsQueryTask().execute(SearchUrl);
    }



    public class NewsQueryTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String newsSearchResults = null;
            try {
                newsSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return newsSearchResults;
        }

        @Override
        protected void onPostExecute(String newsSearchResults) {
            if (newsSearchResults != null && !newsSearchResults.equals("")) {

            }
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
            makeNewsSearchQuery();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void populateRecyclerView(String searchResults){
        Log.d("mycode", searchResults);
        news = JsonUtils.parseNews(searchResults);
        mNewsRecyclerViewAdapter.mNews.addAll(news);
        mNewsRecyclerViewAdapter.notifyDataSetChanged();
    }


}
